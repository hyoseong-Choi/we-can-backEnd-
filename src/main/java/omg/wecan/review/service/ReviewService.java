package omg.wecan.review.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import omg.wecan.challenge.entity.Challenge;
import omg.wecan.challenge.repository.ChallengeRepository;
import omg.wecan.review.dto.ReviewCreateDto;
import omg.wecan.review.dto.ReviewDto;
import omg.wecan.review.entity.Review;
import omg.wecan.review.repository.ReviewRepository;
import omg.wecan.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ChallengeRepository challengeRepository;

    public ReviewDto createReview(User user, ReviewCreateDto reviewDto) {
        Long challengeId = reviewDto.getChallengeId();

        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new IllegalArgumentException("Challenge not found with id: " + challengeId));

        Review review = new Review(user, challenge, reviewDto.getTitle(), reviewDto.getContent());

        Review createdReview = reviewRepository.saveAndFlush(review);

        return new ReviewDto(createdReview);
    }

    public ReviewDto updateReview(Long reviewId, User user, ReviewCreateDto reviewDto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found with id: " + reviewId));

        if (!review.getUser().equals(user)) {
            throw new IllegalArgumentException("This user is not authorized to update this review");
        }

        if (!review.getChallenge().getId().equals(reviewDto.getChallengeId())) {
            throw new IllegalArgumentException("Incorrect challengeId.");
        }

        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());

        Review updatedReview = reviewRepository.save(review);

        return new ReviewDto(updatedReview);
    }


    @Transactional
    public void deleteReview(Long reviewId, User user) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found with id: " + reviewId));

        if (!review.getUser().equals(user)) {
            throw new IllegalArgumentException("This user is not authorized to delete this review");
        }

        reviewRepository.delete(review);
    }

    public List<ReviewDto> getLatestReviews(int count) {
        List<Review> allReviews = reviewRepository.findAllByOrderByCreatedAtDesc();
        if (allReviews.isEmpty()) {
            throw new RuntimeException("No reviews found.");
        }
        List<Review> latestReviews = allReviews.stream()
                .limit(count)
                .toList();

        return latestReviews.stream()
                .map(ReviewDto::new)
                .collect(toList());
    }

    public List<ReviewDto> getReviewsByUser(Long userId) {
        List<Review> userReview = reviewRepository.findByUserUserId(userId);
        if (userReview.isEmpty()) {
            throw new RuntimeException("No reviews found.");
        }
        return userReview.stream()
                .map(ReviewDto::new)
                .collect(toList());
    }

    public List<ReviewDto> getAllReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable).map(ReviewDto::new).getContent();
    }

}
