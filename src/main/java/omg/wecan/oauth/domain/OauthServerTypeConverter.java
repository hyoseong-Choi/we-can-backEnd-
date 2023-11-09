package omg.wecan.oauth.domain;

import omg.wecan.oauth.kakao.domain.OauthServerType;
import org.springframework.core.convert.converter.Converter;

public class OauthServerTypeConverter implements Converter<String, OauthServerType> {
    @Override
    public OauthServerType convert(String source) {
        return OauthServerType.fromName(source);
    }
}
