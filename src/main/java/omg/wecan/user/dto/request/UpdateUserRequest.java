package omg.wecan.user.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UpdateUserRequest {
    @Size(max = 30, message = "닉네임은 30자 이내여야합니다.")
    private String nickName;

    private MultipartFile profileImage;
}
