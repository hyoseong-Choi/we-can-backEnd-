package omg.wecan.review.dto;

import lombok.Data;
import omg.wecan.review.entity.Review;

import java.time.LocalDateTime;

@Data
public class ReviewDto {
    private Long reviewId;
    private Long userId;
    private Long challengeId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ReviewDto() {
    }

    public ReviewDto(Review review) {
        this.reviewId = review.getReviewId();
        this.userId = review.getUser().getUserId();
        this.challengeId = review.getChallenge().getId();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.createdAt= review.getCreatedAt();
        this.updatedAt= review.getUpdatedAt();
    }
}

