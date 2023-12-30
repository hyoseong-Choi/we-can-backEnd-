package omg.wecan.challenge.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import omg.wecan.user.entity.User;

@Entity
@Data
@NoArgsConstructor
public class DislikeCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dislike_check_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "check_id")
    private ChallengeCheck challengeCheck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public DislikeCheck(User user, ChallengeCheck challengeCheck) {
        this.user = user;
        this.challengeCheck = challengeCheck;
    }


}