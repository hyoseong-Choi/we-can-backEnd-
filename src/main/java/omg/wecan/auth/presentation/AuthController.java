package omg.wecan.auth.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omg.wecan.auth.Testuser.entity.TestUser;
import omg.wecan.auth.Testuser.service.TestUserService;
import omg.wecan.auth.dto.LoginUser;
import omg.wecan.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final TestUserService testUserService;

    @GetMapping("/info")
    public void tmpController(@AuthenticationPrincipal LoginUser loginUser){

        log.info("here");

        Long id = loginUser.getId();

        TestUser testUser = testUserService.findById(id);
    }

}
