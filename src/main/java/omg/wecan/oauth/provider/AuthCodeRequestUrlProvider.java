package omg.wecan.oauth.provider;

import omg.wecan.oauth.kakao.domain.OauthServerType;

public interface AuthCodeRequestUrlProvider {
    OauthServerType supportServer();
    String provide();
}
