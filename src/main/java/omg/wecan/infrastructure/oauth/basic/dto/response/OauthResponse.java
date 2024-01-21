package omg.wecan.infrastructure.oauth.basic.dto.response;

import lombok.Getter;
import omg.wecan.jwt.domain.AuthToken;
import omg.wecan.user.entity.User;

@Getter
public class OauthResponse {
    private final AuthToken authToken;
    private final String nickName;
    private final Long userId;

    public OauthResponse(AuthToken authToken, User user) {
        this.authToken = authToken;
        this.nickName = user.getNickName();
        this.userId = user.getUserId();
    }
}
