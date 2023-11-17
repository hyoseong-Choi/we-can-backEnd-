package omg.wecan.infrastructure.oauth.basic.provider;

import omg.wecan.infrastructure.oauth.basic.domain.OauthServerType;

public interface AuthCodeRequestUrlProvider {
    OauthServerType supportServer();
    String provide();
}
