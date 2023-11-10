package omg.wecan.global.config;

import lombok.RequiredArgsConstructor;
import omg.wecan.infrastructure.oauth.basic.domain.OauthServerTypeConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final List<String> corsOrigins;

    public WebConfig(@Value("${cors.allow-origin.urls}") final List<String> corsOrigins){
        this.corsOrigins = corsOrigins;
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] corsOriginPattern = corsOrigins.stream().toArray(String[]::new);

        registry.addMapping("/**")
                .allowedOrigins(corsOriginPattern)
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.PATCH.name()
                )
                .allowCredentials(true)
                .exposedHeaders("*");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new OauthServerTypeConverter());
    }
}