package omg.wecan.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCertificationInput {
    @NotBlank
    private String email;
    @NotBlank
    private String name;
}
