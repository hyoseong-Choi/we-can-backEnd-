package omg.wecan.recruit.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import omg.wecan.global.entity.BaseEntity;
import omg.wecan.user.entity.User;

@Entity
@NoArgsConstructor
@Getter
public class Heart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "heart_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_id")
    private Recruit recruit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static Heart createHeart(User user, Recruit recruit) {
        Heart heart = new Heart();
        heart.user = user;
        heart.recruit = recruit;
        heart.setCreatedAt();
        heart.setUpdatedAt();
        return heart;
    }
}
