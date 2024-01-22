package omg.wecan.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omg.wecan.auth.dto.authResponse.AuthResponse;
import omg.wecan.auth.presentation.AuthenticationPrincipal;
import omg.wecan.jwt.domain.AuthToken;
import omg.wecan.jwt.service.JWTService;
import omg.wecan.user.dto.*;
import omg.wecan.user.dto.request.SignInDto;
import omg.wecan.user.dto.request.SignUpDto;
import omg.wecan.user.dto.request.UpdateUserRequest;
import omg.wecan.user.dto.request.UserDto;
import omg.wecan.user.dto.response.UserProfileResponse;
import omg.wecan.user.dto.response.UserResponse;
import omg.wecan.user.entity.User;
import omg.wecan.user.service.UserFindPasswordService;
import omg.wecan.user.service.UserService;
import omg.wecan.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private final UserService userService;
    private final UserFindPasswordService userFindPasswordService;
    private final JWTService jwtService;

    @PostMapping("/user/sign-up")
    public ResponseEntity<ApiResponse<UserResponse>> signUpUser(@Valid @RequestBody SignUpDto signUpDto) {
        User user = signUpDto.toUser();
        User savedUser = userService.save(user);
        return ResponseEntity.ok().body(ApiResponse.success(new UserResponse(savedUser)));
    }

    @PostMapping("/user/sign-in")
    public ResponseEntity<ApiResponse<AuthResponse>> signInUser(@RequestBody SignInDto signInDto) {
        final String email = signInDto.getEmail();
        final String password = signInDto.getPassword();
        final String fcmToken = signInDto.getFcmToken();

        User user = userService.login(email, password, fcmToken);

        AuthToken authToken = jwtService.createAuthToken(user.getUserId());
        userService.updateRefreshToken(user.getUserId(), authToken.getRefreshToken());

        AuthResponse innerResponse = new AuthResponse(authToken, user);

        return ResponseEntity.ok().body(ApiResponse.success(innerResponse));
    }
    
    @PostMapping("/user/certification")
    public ResponseEntity<ApiResponse<UserCertificationInput>> userCertification(@Valid @RequestBody UserCertificationInput userCertificationInput) {
        return ResponseEntity.ok(ApiResponse.success(userFindPasswordService.certifyUser(userCertificationInput)));
    }
    
    @PostMapping("/user/certification-mail")
    public ResponseEntity<ApiResponse<CertificationMailOutput>> sendCertificationNum(@Valid @RequestBody EmailCertificationInput emailCertificationInput) {
        CertificationMailOutput certificationMailOutput = userFindPasswordService.createMail(emailCertificationInput);
        userFindPasswordService.mailSend(certificationMailOutput);
        return ResponseEntity.ok(ApiResponse.success(certificationMailOutput));
    }

    @PostMapping("/user/certification-num")
    public ResponseEntity<ApiResponse<String>> enterCertificationNum(@Valid @RequestBody ValidateCertificationNumInput validateCertificationNumInput) {
        return ResponseEntity.ok(ApiResponse.success(userFindPasswordService.validateCertificationNum(validateCertificationNumInput)));
    }

    @PatchMapping("/user/password")
    public ResponseEntity<ApiResponse<NewPasswordInput>> changePassword(@Valid @RequestBody NewPasswordInput newPasswordInput) {
        userFindPasswordService.updatePassword(newPasswordInput);
        return ResponseEntity.ok(ApiResponse.success(newPasswordInput));
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity<String> deleteUser(@Valid @RequestBody UserDto userDto) {
        // 아이디 비번 확인하고 탈퇴
        User user = userService.login(userDto.getEmail(), userDto.getPassword(), null);
        userService.deleteUser(user);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/user/profile")
    public ResponseEntity<ApiResponse<UserProfileResponse>> getUserProfile(@AuthenticationPrincipal User user){
        UserProfileResponse response = userService.getUserProfile(user.getUserId());
        return ResponseEntity.ok(ApiResponse.success(response));
    }
    @PutMapping("/user/profile")
    public ResponseEntity<ApiResponse<UserProfileResponse>> updateUserProfile(@AuthenticationPrincipal User user,
                                                                              @Valid @ModelAttribute UpdateUserRequest request) {
        UserProfileResponse response = userService.updateUserProfile(user.getUserId(), request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
