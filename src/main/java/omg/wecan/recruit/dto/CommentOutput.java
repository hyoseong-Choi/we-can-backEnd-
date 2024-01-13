package omg.wecan.recruit.dto;

import lombok.Data;
import omg.wecan.recruit.entity.RecruitComment;

@Data
public class CommentOutput {
    private Long id;
    private String userName;
    private String content;
    
    public CommentOutput(RecruitComment recruitComment) {
        this.id = recruitComment.getId();
        this.userName = recruitComment.getUser().getNickName();
        this.content = recruitComment.getContent();
    }
}
