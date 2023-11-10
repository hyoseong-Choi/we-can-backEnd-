package omg.wecan.infrastructure.oauth.basic.service;

import lombok.RequiredArgsConstructor;
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
