package omg.wecan.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omg.wecan.auth.dto.LoginUser;
import omg.wecan.auth.presentation.AuthenticationPrincipal;
import omg.wecan.user.dto.CertificationMailOutput;
import omg.wecan.user.dto.EmailCertificationInput;
import omg.wecan.user.dto.NewPasswordInput;
import omg.wecan.user.dto.UserCertificationInput;
import omg.wecan.user.entity.User;
import omg.wecan.user.service.UserFindPasswordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserFindPasswordService userFindPasswordService;

    @PostMapping("/user/certification")
    public UserCertificationInput userCertification(@AuthenticationPrincipal User loginUser, @Valid @RequestBody UserCertificationInput userCertificationInput) {
        return userFindPasswordService.certifyUser(userCertificationInput);
    }
    
    @PostMapping("/user/certification-mail")
    public CertificationMailOutput sendCertificationNum(@AuthenticationPrincipal User loginUser, @Valid @RequestBody EmailCertificationInput emailCertificationInput) {
        CertificationMailOutput certificationMailOutput = userFindPasswordService.createMail(emailCertificationInput);
        userFindPasswordService.mailSend(certificationMailOutput);
        return certificationMailOutput;
    }

    @PostMapping("/user/certification-num")
    public String enterCertificationNum(@AuthenticationPrincipal User loginUser, @Valid @RequestBody String certificationNum) {
        return userFindPasswordService.validateCertificationNum(certificationNum);
    }
    
    @PatchMapping("/user/password")
    public NewPasswordInput changePassword(@AuthenticationPrincipal User loginUser, @Valid @RequestBody NewPasswordInput newPasswordInput) {
        //토큰으로 유저 인증하고 레포에서 유저 이메일 가져와야함(서비스로 옮길것)
        userFindPasswordService.updatePassword(newPasswordInput);
        return newPasswordInput;
    }

}
