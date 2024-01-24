package omg.wecan.auth.dto.authResponse;

import lombok.Getter;
import omg.wecan.jwt.domain.AuthToken;
import omg.wecan.user.entity.User;

@Getter
public class AuthResponse {
    private final AuthToken authToken;
    private final String nickName;
    private final String email;
    private final String phone;
    private final Long userId;
    private final Long candy;

    public AuthResponse(AuthToken authToken, User user) {
        this.authToken = authToken;
        this.nickName = user.getNickName();
        this.userId = user.getUserId();
        this.candy = user.getCandy();
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }
}
