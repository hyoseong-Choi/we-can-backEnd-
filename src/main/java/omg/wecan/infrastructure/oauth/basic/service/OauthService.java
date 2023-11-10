package omg.wecan.infrastructure.oauth.basic.service;

import lombok.RequiredArgsConstructor;
import omg.wecan.auth.Testuser.entity.TestUser;
import omg.wecan.auth.Testuser.service.TestUserService;
import omg.wecan.infrastructure.oauth.basic.provider.AuthCodeRequestUrlProviderComposite;
import omg.wecan.infrastructure.oauth.basic.repository.OauthMemberRepository;
import omg.wecan.infrastructure.oauth.basic.domain.client.OauthMemberClientComposite;
import omg.wecan.infrastructure.oauth.basic.entity.OauthMember;
import omg.wecan.infrastructure.oauth.basic.domain.OauthServerType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OauthService {
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final OauthMemberRepository oauthMemberRepository;
    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final TestUserService testUserService;

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

    public TestUser login(OauthServerType oauthServerType, String authCode) {
        OauthMember oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode);

        TestUser testUser = testUserService.findByOauthServerIdAndSocial(
                        oauthMember.oauthId().getOauthServerId(),
                        oauthServerType.toString())
                .orElseGet(() -> testUserService.save(oauthMember.toUser()));

        return testUser;
    }
}
