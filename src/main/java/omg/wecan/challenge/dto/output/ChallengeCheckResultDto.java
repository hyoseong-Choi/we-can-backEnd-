package omg.wecan.challenge.dto.output;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import omg.wecan.challenge.dto.ChallengeCheckImageDto;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChallengeCheckResultDto {
    private Long challengeId;
    private ChallengeCheckImageDto challengeCheckResult;

}