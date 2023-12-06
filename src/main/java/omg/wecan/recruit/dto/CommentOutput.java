package omg.wecan.recruit.dto;

import lombok.Data;
import omg.wecan.recruit.entity.RecruitComment;

@Data
public class CommentOutput {
    private String userName;
    private String content;
    
    public CommentOutput(RecruitComment recruitComment) {
        this.userName = recruitComment.getUser().getName();
        this.content = recruitComment.getContent();
    }
}
