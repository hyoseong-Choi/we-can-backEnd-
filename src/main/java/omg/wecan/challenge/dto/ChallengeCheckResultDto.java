package omg.wecan.challenge.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import omg.wecan.challenge.entity.ChallengeCheck;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChallengeCheckResultDto {
    private Long challengeCheckId;
    private Long challengeId;
    private ChallengeCheckImageDto challengeCheckImage;

    public ChallengeCheckResultDto(ChallengeCheck challengeCheck, ChallengeCheckImageDto challengeCheckImage) {
        this.challengeCheckId = challengeCheck.getId();
        this.challengeId = challengeCheck.getChallenge().getId();
        this.challengeCheckImage = challengeCheckImage;
    }
}