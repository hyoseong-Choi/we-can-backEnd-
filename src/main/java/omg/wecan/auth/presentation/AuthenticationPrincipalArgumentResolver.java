package omg.wecan.auth.presentation;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import omg.wecan.auth.Testuser.entity.ROLE;
import omg.wecan.auth.Testuser.entity.TestUser;
import omg.wecan.auth.Testuser.service.TestUserService;
import omg.wecan.auth.dto.LoginUser;
import omg.wecan.auth.exception.UserRoleException;
import omg.wecan.jwt.exception.InvalidTokenException;
import omg.wecan.jwt.service.JWTService;
import omg.wecan.user.service.UserService;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


@Component
@RequiredArgsConstructor
public class AuthenticationPrincipalArgumentResolver implements HandlerMethodArgumentResolver {

    private final JWTService jwtService;
    private final TestUserService userService;
    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticationPrincipal.class);
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer,
                                  final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String accessToken = AuthorizationExtractor.extract(request);

        jwtService.validateToken(accessToken);
        Long id = jwtService.extractId(accessToken).orElseThrow(
                () -> {
                    throw new InvalidTokenException("토큰에 이메일이 없습니다");
                }
        );

        return new LoginUser(id);
    }
}
