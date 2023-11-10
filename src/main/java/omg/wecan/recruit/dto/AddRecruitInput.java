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
public class AddRecruitInput {
    @NotBlank
    String charityName;
    @NotBlank
    ChallengeType challengetype;
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    LocalDate challengeStartDate;
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    LocalDate challengeEndDate;
    @NotBlank
    int minPeople;
    @NotBlank
    String checkDay;
    @NotBlank
    PaymentType paymentType;
    
    String content;
    @NotBlank
    String coverImageEndpoint;
    @NotNull
    @Positive
    int fine;
}
