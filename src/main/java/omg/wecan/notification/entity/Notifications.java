package omg.wecan.notification.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import omg.wecan.global.entity.BaseEntity;
import omg.wecan.user.entity.User;
import omg.wecan.util.event.RecruitCommentEvent;


@Entity
@NoArgsConstructor
@Getter
public class Notifications extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User targetUser;
    private String title;
    private String content;
    
    public Notifications(RecruitCommentEvent recruitCommentEvent) {
        this.targetUser = recruitCommentEvent.getUser();
        this.title = "누군가 " + recruitCommentEvent.getUser().getNickName() + " 님을 멘션했어요.";
        this.content = recruitCommentEvent.getContent();
    }
}
