package omg.wecan.oauth.kakao.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth.kakao")
public record KakaoOauthConfig(
    String redirectUri,
    String clientId,
    String clientSecret,
    String[] scope
) {}
