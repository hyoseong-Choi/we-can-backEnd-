package omg.wecan.auth.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omg.wecan.auth.dto.LoginUser;
import omg.wecan.user.entity.User;
import omg.wecan.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    @GetMapping("/info")
    public void tmpController(@AuthenticationPrincipal User loginUser){

        log.info("here");
    }
}
