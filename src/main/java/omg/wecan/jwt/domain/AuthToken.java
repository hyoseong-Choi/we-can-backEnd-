package omg.wecan.jwt.domain;

import lombok.Getter;

@Getter
public class AuthToken {
    private final String accessToken;
    private final String refreshToken;

    public AuthToken(final String accessToken, final String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
