package omg.wecan.auth.dto.authResponse;

import lombok.Getter;
import omg.wecan.jwt.domain.AuthToken;

@Getter
public class AuthResponse {
    private final AuthToken authToken;

    public AuthResponse(AuthToken authToken) {
        this.authToken = authToken;
    }
}
