package omg.wecan.review.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReviewCreateDto {
    @NotNull(message = "challengeId가 null입니다.")
    private Long challengeId;

    @NotNull(message = "title이 null입니다.")
    @Size(max = 20)
    private String title;

    @NotNull(message = "content가 null입니다.")
    @Size(max = 500)
    private String content;
}
