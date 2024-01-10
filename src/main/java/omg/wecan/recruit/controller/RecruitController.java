package omg.wecan.recruit.controller;

import lombok.RequiredArgsConstructor;
import omg.wecan.auth.presentation.AuthenticationPrincipal;
import omg.wecan.recruit.dto.*;
import omg.wecan.recruit.service.RecruitService;
import omg.wecan.user.entity.User;
import omg.wecan.util.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RecruitController {
    private final RecruitService recruitService;
    
    @PostMapping("/recruit")
    public ResponseEntity<ApiResponse<RecruitDetailOutput>> recruitAdd(@AuthenticationPrincipal User loginUser, @Validated @ModelAttribute RecruitInput recruitInput) {
        return ResponseEntity.ok(ApiResponse.success(recruitService.addRecruit(loginUser, recruitInput)));
    }
    
    @PatchMapping("/recruit")
    public ResponseEntity<ApiResponse<Long>> recruitUpdate(@AuthenticationPrincipal User loginUser, @Validated @ModelAttribute RecruitInput recruitInput) {
        return ResponseEntity.ok(ApiResponse.success(recruitService.updateRecruit(recruitInput)));
    }
    
    @DeleteMapping("/recruit/{id}")
    public ResponseEntity<ApiResponse<Long>> recruitDelete(@AuthenticationPrincipal User loginUser, @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(recruitService.deleteRecruit(id)));
    }
    
    @GetMapping("/recruit/{id}")
    public ResponseEntity<ApiResponse<RecruitDetailOutput>> recruitDetails(@AuthenticationPrincipal User loginUser, @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(recruitService.findRecruitDetail(loginUser, id)));
    }
    
    @GetMapping("/recruits/home")
    public ResponseEntity<ApiResponse<List<RecruitOutput>>> recruitFindThree(@AuthenticationPrincipal User loginUser) {
        return ResponseEntity.ok(ApiResponse.success(recruitService.findThreeRecruit(loginUser)));
    }
    
    @GetMapping("/recruits")
    public ResponseEntity<ApiResponse<Page<RecruitOutput>>> recruitFind(@AuthenticationPrincipal User loginUser, @ModelAttribute RecruitFindCond recruitFindCond, @PageableDefault(size = 12)Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(recruitService.findRecruit(loginUser, recruitFindCond, pageable)));
    }

    @PostMapping("/recruit/comment")
    public ResponseEntity<ApiResponse<CommentOutput>> recruitCommentAdd(@AuthenticationPrincipal User loginUser, @RequestBody CommentAddInput commentAddInput) {
        return ResponseEntity.ok(ApiResponse.success(recruitService.addRecruitComment(loginUser, commentAddInput)));
    }
    
    @PostMapping("/recruit/participation")
    public ResponseEntity<ApiResponse<Integer>> participateAdd(@AuthenticationPrincipal User loginUser, @RequestBody AddParticipateInput addParticipateInput) {
        return ResponseEntity.ok(ApiResponse.success(recruitService.addParticipate(loginUser, addParticipateInput)));
    }

    @DeleteMapping("/recruit/participation")
    public ResponseEntity<ApiResponse<Integer>> participateDelete(@AuthenticationPrincipal User loginUser, @RequestBody DeleteParticipateAndHeartInput deleteParticipateAndHeartInput) {
        return ResponseEntity.ok(ApiResponse.success(recruitService.deleteParticipate(loginUser, deleteParticipateAndHeartInput)));
    }
    
    @PostMapping("/recruit/heart")
    public ResponseEntity<ApiResponse<Integer>> heartAdd(@AuthenticationPrincipal User loginUser, @RequestBody AddHeartInput addHeartInput) {
        return ResponseEntity.ok(ApiResponse.success(recruitService.addHeart(loginUser, addHeartInput)));
    }

    @DeleteMapping("/recruit/heart")
    public ResponseEntity<ApiResponse<Integer>> heartDelete(@AuthenticationPrincipal User loginUser, @RequestBody DeleteParticipateAndHeartInput deleteParticipateAndHeartInput) {
        return ResponseEntity.ok(ApiResponse.success(recruitService.deleteHeart(loginUser, deleteParticipateAndHeartInput)));
    }
    
    @GetMapping("/recruit/participation")
    public ResponseEntity<ApiResponse<Page<ParticipateRecruitOutput>>> participateRecruitFind(@AuthenticationPrincipal User loginUser, @PageableDefault(size = 3)Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(recruitService.findParticipateRecruit(loginUser, pageable)));
    }
    
    @GetMapping("/recruit/heart")
    public ResponseEntity<ApiResponse<Page<HeartRecruitOutput>>> heartRecruitFind(@AuthenticationPrincipal User loginUser, @PageableDefault(size = 3)Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(recruitService.findHeartRecruit(loginUser, pageable)));
    }
}
