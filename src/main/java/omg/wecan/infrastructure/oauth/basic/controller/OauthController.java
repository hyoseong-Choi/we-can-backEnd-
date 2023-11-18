package omg.wecan.infrastructure.oauth.basic.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import omg.wecan.infrastructure.oauth.basic.dto.response.OauthResponse;
import omg.wecan.jwt.domain.AuthToken;
import omg.wecan.jwt.service.JWTService;
import omg.wecan.infrastructure.oauth.basic.service.OauthService;
import omg.wecan.infrastructure.oauth.basic.domain.OauthServerType;
import omg.wecan.user.entity.User;
import omg.wecan.user.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/oauth")
@RestController
public class OauthController {

    private final OauthService oauthService;
    private final UserService userService;
    private final JWTService jwtService;

    @Value("${jwt.access.header}")
    private static String ACCESS_TOKEN_HEADER;

    @Value("${jwt.refresh.header}")
    private static String REFRESH_TOKEN_HEADER;

    @SneakyThrows
    @GetMapping("/{oauthServerType}")
    ResponseEntity<Void> redirectAuthCodeRequestUrl(
            @PathVariable OauthServerType oauthServerType,
            HttpServletResponse response
    ) {
        System.out.println("oauthServerType = " + oauthServerType);
        String redirectUrl = oauthService.getAuthCodeRequestUrl(oauthServerType);
        response.sendRedirect(redirectUrl);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login/{oauthServerType}")
    ResponseEntity<OauthResponse> login(
            @PathVariable OauthServerType oauthServerType,
            @RequestParam("code") String code
            ) {
        User user = oauthService.login(oauthServerType, code);

        AuthToken authToken = jwtService.createAuthToken(user.getUserId());
        userService.updateRefreshToken(user.getUserId(), authToken.getRefreshToken());

        OauthResponse response = new OauthResponse(authToken);

        return ResponseEntity.ok(response);
    }
}
