package omg.wecan.recruit.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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
    private String challengeType;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private String challengeStartDate;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private String challengeEndDate;
    @NotNull
    private int minPeople;
    @NotBlank
    private String checkDay;
    @NotBlank
    private String paymentType;
    
    private String content;
    @NotNull
    private MultipartFile coverImage;
    @NotNull
    private int fine;
}
