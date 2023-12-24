package omg.wecan.challenge.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import omg.wecan.challenge.entity.Challenge;

import java.time.LocalDate;

@Data
public class ChallengeDto {
    private Long challengeId;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;

    public static ChallengeDto fromEntity(Challenge challenge) {
        ChallengeDto challengeDto = new ChallengeDto();
        challengeDto.setChallengeId(challenge.getId());
        challengeDto.setTitle(challenge.getTitle());
        challengeDto.setStartDate(challenge.getStartDate());
        challengeDto.setEndDate(challenge.getEndDate());
        return challengeDto;
    }
}
