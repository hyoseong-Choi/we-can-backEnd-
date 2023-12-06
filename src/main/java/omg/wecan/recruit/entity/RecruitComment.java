package omg.wecan.recruit.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import omg.wecan.global.entity.BaseEntity;
import omg.wecan.recruit.dto.CommentAddInput;
import omg.wecan.user.entity.User;

@Entity
@NoArgsConstructor
@Getter
public class RecruitComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruitComment_id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_id")
    private Recruit recruit;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String content;
    private Long parentCommentId;//대댓글
    private int depth; //대댓글. parentComment의 depth+1

    public static RecruitComment createRecruitComment(User user, Recruit recruit, CommentAddInput commentAddInput) {
        RecruitComment recruitComment = new RecruitComment();
        recruitComment.user = user;
        recruitComment.recruit = recruit;
        recruitComment.content = commentAddInput.getContent();
//        if (commentAddInput.getParentCommentId() != null) {
//            recruitComment.parentCommentId = commentAddInput.getParentCommentId();
//        }
//        if (commentAddInput.getDepth() != null) {
//            recruitComment.depth = commentAddInput.getDepth();
//        }

        return recruitComment;
    }
}
