package omg.wecan.recruit.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import omg.wecan.recruit.Enum.ChallengeType;
import omg.wecan.recruit.Enum.PaymentType;

import java.time.LocalDate;

@Data
public class RecruitInput {
    
    @Positive
    private Long id;
    @NotBlank
    private String charityName;
    @NotBlank
    private String title;
    @NotBlank
    private ChallengeType challengetype;
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate challengeStartDate;
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate challengeEndDate;
    @NotBlank
    private int minPeople;
    @NotBlank
    private String checkDay;
    @NotBlank
    private PaymentType paymentType;
    
    private String content;
    @NotBlank
    private String coverImageEndpoint;
    @NotNull
    @Positive
    private int fine;
}
