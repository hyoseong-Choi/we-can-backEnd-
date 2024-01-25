package omg.wecan.challenge.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import omg.wecan.challenge.dto.ChallengeCheckImageDto;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChallengeCheckRoomDto {
    private Long challengeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<ChallengeCheckImageDto> challengeChecks;

}