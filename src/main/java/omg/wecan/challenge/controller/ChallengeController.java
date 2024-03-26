package omg.wecan.challenge.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omg.wecan.auth.presentation.AuthenticationPrincipal;
import omg.wecan.challenge.dto.input.ChallengeCheckExemptionDto;
import omg.wecan.challenge.dto.input.ChallengeCheckIdDto;
import omg.wecan.challenge.dto.input.ChallengeCheckDto;
import omg.wecan.challenge.dto.input.CheckDislikeExemptionDto;
import omg.wecan.challenge.dto.output.*;
import omg.wecan.challenge.service.ChallengeService;
import omg.wecan.user.entity.User;
import omg.wecan.util.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("checkroom/{challengeId}/{checkDate}")
    public ResponseEntity<ApiResponse<ChallengeCheckRoomDto>> getChallengeCheckRoomInfo(@AuthenticationPrincipal User user, @PathVariable Long challengeId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkDate) {
        return ResponseEntity.ok(ApiResponse.success(challengeService.getChallengeCheckRoomInfo(user, challengeId, checkDate)));
    }

    @PostMapping("checkroom/upload")
    public ResponseEntity<ApiResponse<ChallengeCheckResultDto>> saveChallengeCheck(
            @AuthenticationPrincipal User user,
            @ModelAttribute ChallengeCheckDto challengeCheckInputDto
    ) {
        return ResponseEntity.ok(ApiResponse.success(challengeService.saveChallengeCheck(user, challengeCheckInputDto)));
    }

    @PostMapping("checkroom/exemption")
    public ResponseEntity<ApiResponse<ChallengeCheckResultDto>> challengeCheckExemption (
            @AuthenticationPrincipal User user,
            @RequestBody @Valid ChallengeCheckExemptionDto challengeCheckExemptionDto
    ) {
        return ResponseEntity.ok(ApiResponse.success(challengeService.challengeCheckExemption(user, challengeCheckExemptionDto)));
    }


    @PostMapping("checkroom/dislike")
    public ResponseEntity<ApiResponse<ChallengeCheckResultDto>> dislikeChallengeCheck(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid ChallengeCheckIdDto challengeCheckIdDto
    ) {
        return ResponseEntity.ok(ApiResponse.success(challengeService.dislikeChallengeCheck(user, challengeCheckIdDto.getChallengeCheckId())));
    }

    @PostMapping("checkroom/dislike/exemption")
    public ResponseEntity<ApiResponse<ChallengeCheckResultDto>> dislikeExemption(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid CheckDislikeExemptionDto checkDislikeExemptionDto
    ) {
        return ResponseEntity.ok(ApiResponse.success(challengeService.dislikeExemption(user, checkDislikeExemptionDto)));
    }
}
