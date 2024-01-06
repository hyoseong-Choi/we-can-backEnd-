package omg.wecan.challenge.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import omg.wecan.challenge.dto.UserChallengeDto;
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
    private int failNum;
    private boolean payed;
  
    public static UserChallenge createUserChallenge(Participate participate, Challenge challenge) {
        UserChallenge userChallenge = new UserChallenge();
        userChallenge.user = participate.getUser();
        userChallenge.challenge = challenge;
        userChallenge.leader = participate.isLeader();
        userChallenge.failNum = 0;
        userChallenge.payed = false;
        return userChallenge;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }
    public UserChallengeDto toDto() {
        UserChallengeDto userChallengeDto = new UserChallengeDto();

        userChallengeDto.setChallengeId(this.getChallenge().getId());
        userChallengeDto.setUserId(this.getUser().getUserId());
        userChallengeDto.setLeader(this.isLeader());
        userChallengeDto.setFailNum(this.getFailNum());
        userChallengeDto.setPayed(this.isPayed());

        return userChallengeDto;
    }

    public void increaseFailNum() {
        this.failNum++;
    }
}