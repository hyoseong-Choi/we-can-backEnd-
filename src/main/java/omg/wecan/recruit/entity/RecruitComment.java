package omg.wecan.recruit.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import omg.wecan.user.entity.User;

@Entity
@NoArgsConstructor
public class RecruitComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruitComment_id")
    Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_id")
    Recruit recruit;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;
    
    String content;
    Long parentCommentId;//대댓글
    int depth; //대댓글
}
