package omg.wecan.infrastructure.oauth.basic.domain.client;

import omg.wecan.infrastructure.oauth.basic.domain.OauthServerType;
import omg.wecan.infrastructure.oauth.basic.entity.OauthMember;

public interface OauthMemberClient {
    OauthServerType supportServer();

    OauthMember fetch(String code);
}
