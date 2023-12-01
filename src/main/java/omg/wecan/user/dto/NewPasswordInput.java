package omg.wecan.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPasswordInput {
    @NotBlank
    private String email;
    @NotBlank
    private String newPassword;
}
