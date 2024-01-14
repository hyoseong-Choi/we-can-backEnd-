package omg.wecan.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omg.wecan.auth.presentation.AuthenticationPrincipal;
import omg.wecan.review.dto.ReviewCreateDto;
import omg.wecan.review.dto.ReviewDto;
import omg.wecan.review.entity.Review;
import omg.wecan.review.service.ReviewService;
import omg.wecan.user.entity.User;
import omg.wecan.util.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    //리뷰 작성
    @PostMapping()
    public ResponseEntity<ApiResponse<ReviewDto>> createReview(@AuthenticationPrincipal User user, @RequestBody @Valid ReviewCreateDto reviewDto) {
        ReviewDto createdReview = reviewService.createReview(user, reviewDto);
        return ResponseEntity.ok(ApiResponse.success(createdReview));
    }


    //리뷰 수정
    @PatchMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<ReviewDto>> updateReview(@PathVariable Long reviewId, @AuthenticationPrincipal User user, @RequestBody @Valid ReviewCreateDto reviewDto) {
        ReviewDto updatedReview = reviewService.updateReview(reviewId, user, reviewDto);
        return ResponseEntity.ok(ApiResponse.success(updatedReview));
    }

    //리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<ReviewDto>> deleteReview(@PathVariable Long reviewId, @AuthenticationPrincipal User user) {
        reviewService.deleteReview(reviewId, user);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    //리뷰 전체 조회 페이징
    @GetMapping()
    public ResponseEntity<ApiResponse<List<ReviewDto>>> getAllReviews(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<ReviewDto> reviewList  =  reviewService.getAllReviews(PageRequest.of(page, size));
        return ResponseEntity.ok(ApiResponse.success(reviewList));

    }


    // 유저 후기 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<ReviewDto>>> getReviewsByUser(@PathVariable Long userId) {
        List<ReviewDto> userReviews = reviewService.getReviewsByUser(userId);
        return ResponseEntity.ok(ApiResponse.success(userReviews));
    }

    // 최신 리뷰 3개 조회
    @GetMapping("/latest")
    public ResponseEntity<ApiResponse<List<ReviewDto>>> getLatestReviews() {
        List<ReviewDto> latestReviews = reviewService.getLatestReviews(3);
        return ResponseEntity.ok(ApiResponse.success(latestReviews));
    }
}
