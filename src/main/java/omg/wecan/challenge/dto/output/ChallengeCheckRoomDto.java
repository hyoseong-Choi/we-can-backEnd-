package omg.wecan.challenge.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import omg.wecan.challenge.dto.ChallengeCheckImageDto;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChallengeCheckRoomDto {
    private Long challengeId;
    private Long daysUntilDeadline;
    private List<ChallengeCheckImageDto> challengeChecks;

}