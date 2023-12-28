package omg.wecan.recruit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CommentAddInput {
    @NotNull
    @Positive
    Long recruitId;
    @NotBlank
    String content;

//    Long parentCommentId;//대댓글

//    Integer depth; //대댓글.
}
