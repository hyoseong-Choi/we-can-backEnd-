package omg.wecan.user.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileResponse {
    private Long userId;

    private String nickName;

    private String imgEndPoint;

}
