package omg.wecan.challenge.dto.output;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserChallengeDto {
    private Long challengeId;
    private Long userId;
    private boolean leader;
    private int failNum;
    private boolean payed;
}
