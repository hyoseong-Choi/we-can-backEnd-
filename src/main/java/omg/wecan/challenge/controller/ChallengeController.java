package omg.wecan.challenge.controller;

import lombok.RequiredArgsConstructor;
import omg.wecan.auth.presentation.AuthenticationPrincipal;
import omg.wecan.challenge.dto.*;
import omg.wecan.challenge.entity.UserChallenge;
import omg.wecan.challenge.service.ChallengeService;
import omg.wecan.user.entity.User;
import omg.wecan.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenge")
@RequiredArgsConstructor
public class ChallengeController {
    private final ChallengeService challengeService;

    // 유저의 참여 중인 챌린지 조회
    @GetMapping("active/user/{userId}")
    public ResponseEntity<ApiResponse<List<ChallengeDto>>> getActiveChallengeByUser(@PathVariable Long userId) {
        List<ChallengeDto> activeChallenges = challengeService.getActiveChallengesByUser(userId);
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

    // 유저의 참여 예정인 챌린지 조회
    @GetMapping("upcoming/user/{userId}")
    public ResponseEntity<ApiResponse<List<ChallengeDto>>> getUpcomingChallengeByUser(@PathVariable Long userId) {
        List<ChallengeDto> upcomingChallenges = challengeService.getUpcomingChallengesByUser(userId);
        return ResponseEntity.ok(ApiResponse.success(upcomingChallenges));
    }

    // 유저의 참여 완료 챌린지 조회
    @GetMapping("completed/user/{userId}")
    public ResponseEntity<ApiResponse<List<ChallengeDto>>> getCompletedChallengeByUser(@PathVariable Long userId) {
        List<ChallengeDto> completedChallenges = challengeService.getCompletedChallengesByUser(userId);
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
