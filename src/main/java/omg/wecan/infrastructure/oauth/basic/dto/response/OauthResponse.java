package omg.wecan.infrastructure.oauth.basic.dto.response;

import lombok.Getter;
import omg.wecan.jwt.domain.AuthToken;

@Getter
public class OauthResponse {
    private AuthToken authToken;

    public OauthResponse(AuthToken authToken) {
        this.authToken = authToken;
    }
}
