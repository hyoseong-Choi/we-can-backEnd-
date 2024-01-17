package omg.wecan.auth.dto.authResponse;

import lombok.Getter;
import omg.wecan.jwt.domain.AuthToken;
import omg.wecan.user.entity.User;

@Getter
public class AuthResponse {
    private final AuthToken authToken;
    private final String nickName;
    private final Long userId;

    public AuthResponse(AuthToken authToken, User user) {
        this.authToken = authToken;
        this.nickName = user.getNickName();
        this.userId = user.getUserId();
    }
}
