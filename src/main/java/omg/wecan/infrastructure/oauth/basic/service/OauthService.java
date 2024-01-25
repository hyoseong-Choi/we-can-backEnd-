package omg.wecan.infrastructure.oauth.basic.service;

import lombok.RequiredArgsConstructor;
import omg.wecan.infrastructure.oauth.basic.provider.AuthCodeRequestUrlProviderComposite;
import omg.wecan.infrastructure.oauth.basic.repository.OauthMemberRepository;
import omg.wecan.infrastructure.oauth.basic.domain.client.OauthMemberClientComposite;
import omg.wecan.infrastructure.oauth.basic.entity.OauthMember;
import omg.wecan.infrastructure.oauth.basic.domain.OauthServerType;
import omg.wecan.user.entity.User;
import omg.wecan.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OauthService {
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final OauthMemberRepository oauthMemberRepository;
    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final UserService userService;

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }
@Transactional
    public User login(OauthServerType oauthServerType, String authCode, String fcm) {
        OauthMember oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode);

        User user = userService.findByOauthServerIdAndSocial(
                        oauthMember.oauthId().getOauthServerId(),
                        oauthServerType.toString())
                .orElseGet(() -> userService.save(oauthMember.toUser()));

        user.setFcmToken(fcm);
        return user;
    }
}
