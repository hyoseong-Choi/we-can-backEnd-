package omg.wecan.challenge.controller;

import lombok.RequiredArgsConstructor;
import omg.wecan.auth.presentation.AuthenticationPrincipal;
import omg.wecan.challenge.dto.*;
import omg.wecan.challenge.service.ChallengeService;
import omg.wecan.user.entity.User;
import omg.wecan.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenge")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChallengeController {
    private final ChallengeService challengeService;

    // 유저의 참여 중인 챌린지 조회
    @GetMapping("active")
    public ResponseEntity<ApiResponse<List<ChallengeDto>>> getActiveChallengeByUser(@AuthenticationPrincipal User user) {
        List<ChallengeDto> activeChallenges = challengeService.getActiveChallengesByUser(user.getUserId());
        return ResponseEntity.ok(ApiResponse.success(activeChallenges));
    }

    @GetMapping("active/{challengeId}")
    public ResponseEntity<ApiResponse<UserChallengeDto>> getUserChallenge(
            @AuthenticationPrincipal User user,
            @PathVariable Long challengeId
    ) {
        UserChallengeDto userChallengeDto = challengeService.getUserChallengeByUserAndChallengeId(user, challengeId);
        return ResponseEntity.ok(ApiResponse.success(userChallengeDto));
    }


    // 유저의 참여 완료 챌린지 조회
    @GetMapping("completed")
    public ResponseEntity<ApiResponse<List<ChallengeDto>>> getCompletedChallengeByUser(@AuthenticationPrincipal User user) {
        List<ChallengeDto> completedChallenges = challengeService.getCompletedChallengesByUser(user.getUserId());
        return ResponseEntity.ok(ApiResponse.success(completedChallenges));
    }

    @GetMapping("info/{challengeId}")
    public ResponseEntity<ApiResponse<ChallengeInfoDto>> getChallengeInfo(
            @AuthenticationPrincipal User user,
            @PathVariable Long challengeId
    ) {
        ChallengeInfoDto challengeInfoDto = challengeService.getChallengeInfo(user, challengeId);
        return ResponseEntity.ok(ApiResponse.success(challengeInfoDto));
    }

    @GetMapping("checkroom/{challengeId}")
    public ResponseEntity<ApiResponse<ChallengeCheckRoomDto>> getChallengeCheckRoomInfo(@PathVariable Long challengeId) {
        ChallengeCheckRoomDto challengeCheckRoomInfo = challengeService.getChallengeCheckRoomInfo(challengeId);
        return ResponseEntity.ok(ApiResponse.success(challengeCheckRoomInfo));
    }

    @PostMapping("checkroom/upload")
    public ResponseEntity<ApiResponse<ChallengeCheckResultDto>> saveChallengeCheck(
            @AuthenticationPrincipal User user,
            @ModelAttribute ChallengeCheckInputDto challengeCheckInputDto
    ) {
        ChallengeCheckResultDto challengeCheckResult = challengeService.saveChallengeCheck(user, challengeCheckInputDto);
        return ResponseEntity.ok(ApiResponse.success(challengeCheckResult));
    }


    @PostMapping("checkroom/dislike/{challengeCheckId}")
    public ResponseEntity<ApiResponse<ChallengeCheckResultDto>> dislikeChallengeCheck(
            @AuthenticationPrincipal User user,
            @PathVariable Long challengeCheckId
    ) {
        ChallengeCheckResultDto challengeCheckResult = challengeService.dislikeChallengeCheck(user, challengeCheckId);
        return ResponseEntity.ok(ApiResponse.success(challengeCheckResult));
    }
}
