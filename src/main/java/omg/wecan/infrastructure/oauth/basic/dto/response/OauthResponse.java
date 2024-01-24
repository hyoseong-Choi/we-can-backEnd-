package omg.wecan.infrastructure.oauth.basic.dto.response;

import lombok.Getter;
import omg.wecan.jwt.domain.AuthToken;
import omg.wecan.user.entity.User;

@Getter
public class OauthResponse {
    private final AuthToken authToken;
    private final String nickName;
    private final Long userId;
    private final String email;
    private final String phone;
    private final Long candy;

    public OauthResponse(AuthToken authToken, User user) {
        this.authToken = authToken;
        this.nickName = user.getNickName();
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.candy = user.getCandy();
    }
}
