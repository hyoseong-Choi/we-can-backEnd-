package omg.wecan.challenge.dto.input;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChallengeCheckExemptionDto {
    private Long challengeId;
    private String exemptionString;
}