package omg.wecan.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import omg.wecan.user.entity.ROLE;
import omg.wecan.user.entity.User;

@Getter
@Setter
@AllArgsConstructor
public class SignUpDto {
    @NotNull(message = "이메일은 null일 수 없습니다.!")
    @Email
    private final String email;

    @NotNull(message = "비밀번호는 null일 수 없습니다.!")
    private final String password;

    @NotEmpty(message = "이름은 공백일 수 없습니다")
    private final String name;

    @NotEmpty(message = "닉네임은 공백일 수 없습니다")
    private final String nickName;

    @NotNull
    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "10 ~ 11 자리의 숫자만 입력 가능합니다.")
    private final String phone;

    public User toUser() {
        return new User(this.email, this.password, this.name, this.nickName, this.phone, null, null, null, ROLE.USER);
    }
}
