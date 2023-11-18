package omg.wecan.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omg.wecan.user.dto.CertificationMailOutput;
import omg.wecan.user.dto.NewPasswordInput;
import omg.wecan.user.dto.UserCertificationInput;
import omg.wecan.user.service.UserFindPasswordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserFindPasswordService userFindPasswordService;
    
    @PostMapping("/user/certification")
    public UserCertificationInput userCertification(@Valid @RequestBody UserCertificationInput userCertificationInput) {
        return userFindPasswordService.certifyUser(userCertificationInput);
    }
    
    @GetMapping("/user/certification")
    public CertificationMailOutput sendCertificationNum() {
        CertificationMailOutput certificationMailOutput = userFindPasswordService.createMail();
        userFindPasswordService.mailSend(certificationMailOutput);
        return certificationMailOutput;
    }
    
    @PatchMapping("/user/password")
    public NewPasswordInput changePassword(@Valid @RequestBody NewPasswordInput newPasswordInput) {
        //토큰으로 유저 인증하고 레포에서 유저 이메일 가져와야함(서비스로 옮길것)
        userFindPasswordService.updatePassword(newPasswordInput);
        return newPasswordInput;
    }
}
