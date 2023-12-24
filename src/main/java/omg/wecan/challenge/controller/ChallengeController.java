package omg.wecan.challenge.controller;

import lombok.RequiredArgsConstructor;
import omg.wecan.auth.presentation.AuthenticationPrincipal;
import omg.wecan.challenge.dto.ChallengeCheckInputDto;
import omg.wecan.challenge.dto.ChallengeCheckResultDto;
import omg.wecan.challenge.service.ChallengeService;
import omg.wecan.challenge.dto.ChallengeDto;
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

    // 챌린지 페이지 조회
//    @GetMapping("info/{challengeId}")
//    public ResponseEntity<ApiResponse<List<ChallengeDetailsDto>>> getChallengeInfo(@PathVariable Long challengeId) {
//        List<ChallengeDetailsDto> challengeInfo = challengeService.getChallengeInfo(challengeId);
//        return ResponseEntity.ok(ApiResponse.success(challengeInfo));
//    }

    @PostMapping("checkroom/upload")
    public ResponseEntity<ApiResponse<ChallengeCheckResultDto>> saveChallengeCheck(
            @AuthenticationPrincipal User user,
            @ModelAttribute ChallengeCheckInputDto challengeCheckInputDto
    ) {
        ChallengeCheckResultDto challengeCheckResult = challengeService.saveChallengeCheck(user, challengeCheckInputDto);
        return ResponseEntity.ok(ApiResponse.success(challengeCheckResult));
    }


}
