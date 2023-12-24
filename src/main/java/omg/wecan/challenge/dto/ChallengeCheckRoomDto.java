package omg.wecan.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import omg.wecan.challenge.entity.ChallengeCheck;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChallengeCheckRoomDto {
    private Long challengeCheckId;
    private Long challengeId;
    private List<ChallengeCheckImageDto> challengeCheckImages;

    public ChallengeCheckRoomDto(ChallengeCheck challengeCheck, List<ChallengeCheckImageDto> challengeCheckImages) {
        this.challengeCheckId = challengeCheck.getId();
        this.challengeId = challengeCheck.getChallenge().getId();
        this.challengeCheckImages = challengeCheckImages;
    }
}