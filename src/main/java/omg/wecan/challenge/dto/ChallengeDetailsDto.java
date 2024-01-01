package omg.wecan.challenge.dto;

import lombok.Data;
import omg.wecan.challenge.entity.Challenge;

import java.time.LocalDate;
import java.util.List;

@Data
public class ChallengeDetailsDto {
    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer donationCandy;

    public static ChallengeDetailsDto fromEntity(Challenge challenge, Integer donationCandy) {
        ChallengeDetailsDto challengeDto = new ChallengeDetailsDto();
        challengeDto.setId(challenge.getId());
        challengeDto.setTitle(challenge.getTitle());
        challengeDto.setStartDate(challenge.getStartDate());
        challengeDto.setEndDate(challenge.getEndDate());
        challengeDto.donationCandy = donationCandy;
        return challengeDto;
    }
}
