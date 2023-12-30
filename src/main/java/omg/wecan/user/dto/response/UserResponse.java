package omg.wecan.user.dto.response;

import lombok.Getter;
import omg.wecan.user.entity.User;

@Getter
public class UserResponse {
    private Long userId;
    private String email;

    public UserResponse(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
    }
}
