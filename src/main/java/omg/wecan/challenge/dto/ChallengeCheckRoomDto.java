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
    private Long challengeId;
    private List<ChallengeCheckImageDto> challengeChecks;

}