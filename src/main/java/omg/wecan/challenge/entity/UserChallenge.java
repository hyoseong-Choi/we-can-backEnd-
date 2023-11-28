package omg.wecan.challenge.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import omg.wecan.recruit.entity.Participate;
import omg.wecan.user.entity.User;

@Entity
@Getter
@NoArgsConstructor
public class UserChallenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Challenge challenge;
    private boolean leader;
    
    public static UserChallenge createUserChallenge(Participate participate, Challenge challenge) {
        UserChallenge userChallenge = new UserChallenge();
        userChallenge.user = participate.getUser();
        userChallenge.challenge = challenge;
        userChallenge.leader = participate.isLeader();
        return userChallenge;
    }
}
