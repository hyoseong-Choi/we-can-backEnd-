package omg.wecan.infrastructure.oauth.basic.dto.response;

import lombok.Getter;
import omg.wecan.auth.Testuser.entity.ROLE;
import omg.wecan.jwt.domain.AuthToken;

@Getter
public class OauthResponse {
    private AuthToken authToken;
    private ROLE userRole;

    public OauthResponse(AuthToken authToken, ROLE userRole) {
        this.authToken = authToken;
        this.userRole = userRole;
    }
}
