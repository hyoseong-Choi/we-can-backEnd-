package omg.wecan.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omg.wecan.auth.presentation.AuthenticationPrincipal;
import omg.wecan.review.dto.ReviewCreateDto;
import omg.wecan.review.dto.ReviewDto;
import omg.wecan.review.entity.Review;
import omg.wecan.review.service.ReviewService;
import omg.wecan.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    //리뷰 작성
    @PostMapping("/create")
    public ResponseEntity<ReviewDto> createReview(@AuthenticationPrincipal User user, @RequestBody @Valid ReviewCreateDto reviewDto) {
        ReviewDto createdReview = reviewService.createReview(user, reviewDto);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    //리뷰 수정
    @PatchMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable Long reviewId, @AuthenticationPrincipal User user, @RequestBody @Valid ReviewCreateDto reviewDto) {
        ReviewDto updatedReview = reviewService.updateReview(reviewId, user, reviewDto);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    //리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId, @AuthenticationPrincipal User user) {
        reviewService.deleteReview(reviewId, user);
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }

    @GetMapping()
    public List<ReviewDto> getAllReviews(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        return reviewService.getAllReviews(PageRequest.of(page, size));
    }



    // 챌린지 후기 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewDto>> getReviewsByUser(@PathVariable Long userId) {
        List<ReviewDto> userReviews = reviewService.getReviewsByUser(userId);
        return ResponseEntity.ok(userReviews);
    }

    // 최신 리뷰 3개 조회
    @GetMapping("/latest")
    public ResponseEntity<List<ReviewDto>> getLatestReviews() {
        List<ReviewDto> latestReviews = reviewService.getLatestReviews(3);
        return ResponseEntity.ok(latestReviews);
    }
}
