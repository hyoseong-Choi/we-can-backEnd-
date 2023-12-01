package omg.wecan.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omg.wecan.auth.dto.authResponse.AuthResponse;
import omg.wecan.jwt.domain.AuthToken;
import omg.wecan.jwt.service.JWTService;
import omg.wecan.user.dto.*;
import omg.wecan.user.dto.request.SignInDto;
import omg.wecan.user.dto.request.SignUpDto;
import omg.wecan.user.entity.User;
import omg.wecan.user.service.UserFindPasswordService;
import omg.wecan.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserFindPasswordService userFindPasswordService;
    private final JWTService jwtService;

    @PostMapping("/user/sign-up")
    public ResponseEntity<User> signUpUser(@Valid @RequestBody SignUpDto signUpDto) {
        User user = signUpDto.toUser();
        User savedUser = userService.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/user/sign-in")
    public ResponseEntity<AuthResponse> signInUser(@Valid @RequestBody SignInDto signInDto) {
        final String email = signInDto.getEmail();
        final String password = signInDto.getPassword();


        User user = userService.login(email, password);

        AuthToken authToken = jwtService.createAuthToken(user.getUserId());
        userService.updateRefreshToken(user.getUserId(), authToken.getRefreshToken());

        AuthResponse response = new AuthResponse(authToken);

        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/user/certification")
    public UserCertificationInput userCertification(@Valid @RequestBody UserCertificationInput userCertificationInput) {
        return userFindPasswordService.certifyUser(userCertificationInput);
    }
    
    @PostMapping("/user/certification-mail")
    public CertificationMailOutput sendCertificationNum(@Valid @RequestBody EmailCertificationInput emailCertificationInput) {
        CertificationMailOutput certificationMailOutput = userFindPasswordService.createMail(emailCertificationInput);
        userFindPasswordService.mailSend(certificationMailOutput);
        return certificationMailOutput;
    }

    @PostMapping("/user/certification-num")
    public String enterCertificationNum(@Valid @RequestBody ValidateCertificationNumInput validateCertificationNumInput) {
        return userFindPasswordService.validateCertificationNum(validateCertificationNumInput);
    }

    @PatchMapping("/user/password")
    public NewPasswordInput changePassword(@Valid @RequestBody NewPasswordInput newPasswordInput) {
        userFindPasswordService.updatePassword(newPasswordInput);
        return newPasswordInput;
    }
}
