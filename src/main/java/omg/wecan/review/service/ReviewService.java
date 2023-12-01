package omg.wecan.review.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import omg.wecan.challenge.entity.Challenge;
import omg.wecan.challenge.repository.ChallengeRepository;
import omg.wecan.exception.customException.CustomException;
import omg.wecan.exception.customException.ErrorCode;
import omg.wecan.review.dto.ReviewCreateDto;
import omg.wecan.review.dto.ReviewDto;
import omg.wecan.review.entity.Review;
import omg.wecan.review.repository.ReviewRepository;
import omg.wecan.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ChallengeRepository challengeRepository;

    public ReviewDto createReview(User user, ReviewCreateDto reviewDto) {
        Long challengeId = reviewDto.getChallengeId();

        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() -> new CustomException(ErrorCode.CHALLENGE_NOT_FOUND, "challengeId: "+challengeId));

        Review review = new Review(user, challenge, reviewDto.getTitle(), reviewDto.getContent());

        Review createdReview = reviewRepository.saveAndFlush(review);

        return new ReviewDto(createdReview);
    }

    public ReviewDto updateReview(Long reviewId, User user, ReviewCreateDto reviewDto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ErrorCode.REVIEW_NOT_FOUND, "reviewId: "+reviewId));

        if (!review.getUser().equals(user)) {
            throw new CustomException(ErrorCode.REVIEW_AUTHOR_MISMATCH);
        }

        if (!review.getChallenge().getId().equals(reviewDto.getChallengeId())) {
            throw new CustomException(ErrorCode.CHALLENGE_AUTHOR_MISMATCH);
        }

        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());

        Review updatedReview = reviewRepository.save(review);

        return new ReviewDto(updatedReview);
    }


    @Transactional
    public void deleteReview(Long reviewId, User user) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new CustomException(ErrorCode.REVIEW_NOT_FOUND, "reviewId: "+reviewId));

        if (!review.getUser().equals(user)) {
            throw new CustomException(ErrorCode.REVIEW_AUTHOR_MISMATCH);
        }

        reviewRepository.delete(review);
    }

    public List<ReviewDto> getLatestReviews(int count) {
        List<Review> allReviews = reviewRepository.findAllByOrderByCreatedAtDesc();
        if (allReviews.isEmpty()) {
            throw new CustomException(ErrorCode.REVIEW_NOT_FOUND);
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
            throw new CustomException(ErrorCode.REVIEW_NOT_FOUND);
        }
        return userReview.stream()
                .map(ReviewDto::new)
                .collect(toList());
    }

    public List<ReviewDto> getAllReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable).map(ReviewDto::new).getContent();
    }

}
