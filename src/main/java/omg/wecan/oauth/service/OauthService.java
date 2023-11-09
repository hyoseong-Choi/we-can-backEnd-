package omg.wecan.oauth.service;

import lombok.RequiredArgsConstructor;
import omg.wecan.oauth.kakao.domain.OauthServerType;
import omg.wecan.oauth.provider.AuthCodeRequestUrlProviderComposite;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }
}
