package omg.wecan.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ValidateCertificationNumInput {
    @NotBlank
    private String email;
    @NotBlank
    private String certificationNum;
}
