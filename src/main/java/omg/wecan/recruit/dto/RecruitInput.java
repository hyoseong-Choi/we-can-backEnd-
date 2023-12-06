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
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate challengeStartDate;
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate challengeEndDate;
    @NotBlank
    @Min(value = 5)
    private int minPeople;
    @NotBlank
    private String checkDay;
    @NotBlank
    private String paymentType;
    
    private String content;
    @NotBlank
    private MultipartFile coverImage;
    @NotNull
    @Min(value = 1)
    private int fine;
}
