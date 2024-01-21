package omg.wecan.challenge.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omg.wecan.auth.presentation.AuthenticationPrincipal;
import omg.wecan.challenge.dto.*;
import omg.wecan.challenge.service.ChallengeService;
import omg.wecan.review.dto.ReviewCreateDto;
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
        return ResponseEntity.ok(ApiResponse.success(challengeService.getActiveChallengesByUser(user.getUserId())));
    }

    @GetMapping("active/{challengeId}")
    public ResponseEntity<ApiResponse<UserChallengeDto>> getUserChallenge(
            @AuthenticationPrincipal User user,
            @PathVariable Long challengeId
    ) {
        return ResponseEntity.ok(ApiResponse.success(challengeService.getUserChallengeByUserAndChallengeId(user, challengeId)));
    }


    // 유저의 참여 완료 챌린지 조회
    @GetMapping("completed")
    public ResponseEntity<ApiResponse<List<ChallengeDto>>> getCompletedChallengeByUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(ApiResponse.success(challengeService.getCompletedChallengesByUser(user.getUserId())));
    }

    @GetMapping("info/{challengeId}")
    public ResponseEntity<ApiResponse<ChallengeInfoDto>> getChallengeInfo(
            @AuthenticationPrincipal User user,
            @PathVariable Long challengeId
    ) {
        return ResponseEntity.ok(ApiResponse.success(challengeService.getChallengeInfo(user, challengeId)));
    }

    @GetMapping("checkroom/{challengeId}")
    public ResponseEntity<ApiResponse<ChallengeCheckRoomDto>> getChallengeCheckRoomInfo(@PathVariable Long challengeId) {
        return ResponseEntity.ok(ApiResponse.success(challengeService.getChallengeCheckRoomInfo(challengeId)));
    }

    @PostMapping("checkroom/upload")
    public ResponseEntity<ApiResponse<ChallengeCheckResultDto>> saveChallengeCheck(
            @AuthenticationPrincipal User user,
            @ModelAttribute ChallengeCheckInputDto challengeCheckInputDto
    ) {
        return ResponseEntity.ok(ApiResponse.success(challengeService.saveChallengeCheck(user, challengeCheckInputDto)));
    }

    @PostMapping("checkroom/item")
    public ResponseEntity<ApiResponse<ChallengeCheckResultDto>> challengeCheckExemption (
            @AuthenticationPrincipal User user,
            @RequestBody @Valid ChallengeCheckExemptionDto challengeCheckExemptionDto
    ) {
        return ResponseEntity.ok(ApiResponse.success(challengeService.challengeCheckExemption(user, challengeCheckExemptionDto)));
    }


    @PostMapping("checkroom/dislike/{challengeCheckId}")
    public ResponseEntity<ApiResponse<ChallengeCheckResultDto>> dislikeChallengeCheck(
            @AuthenticationPrincipal User user,
            @PathVariable Long challengeCheckId
    ) {
        return ResponseEntity.ok(ApiResponse.success(challengeService.dislikeChallengeCheck(user, challengeCheckId)));
    }
}
