package omg.wecan.challenge.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChallengeCheckResultDto {
    private Long challengeId;
    private ChallengeCheckImageDto challengeCheckResult;

}