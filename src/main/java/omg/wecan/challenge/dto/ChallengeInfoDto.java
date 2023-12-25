package omg.wecan.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChallengeInfoDto {
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private int donationCandy;
}