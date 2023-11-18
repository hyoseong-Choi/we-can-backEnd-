package omg.wecan.user.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInDto {
    String email;
    String password;
}
