package omg.wecan.oauth.domain.client;

import omg.wecan.oauth.entity.OauthMember;
import omg.wecan.oauth.kakao.domain.OauthServerType;

public interface OauthMemberClient {
    OauthServerType supportServer();

    OauthMember fetch(String code);
}
