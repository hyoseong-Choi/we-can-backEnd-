package omg.wecan.oauth.service;

import lombok.RequiredArgsConstructor;
import omg.wecan.oauth.domain.client.OauthMemberClientComposite;
import omg.wecan.oauth.entity.OauthMember;
import omg.wecan.oauth.kakao.domain.OauthServerType;
import omg.wecan.oauth.provider.AuthCodeRequestUrlProviderComposite;
import omg.wecan.oauth.repository.OauthMemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OauthService {
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final OauthMemberRepository oauthMemberRepository;
    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

    public Long login(OauthServerType oauthServerType, String authCode) {
        OauthMember oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode);
        OauthMember saved = oauthMemberRepository.findByOauthId(oauthMember.oauthId())
                .orElseGet(() -> oauthMemberRepository.save(oauthMember));
        return saved.id();
    }
}
