package omg.wecan.recruit.controller;

import lombok.RequiredArgsConstructor;
import omg.wecan.auth.presentation.AuthenticationPrincipal;
import omg.wecan.recruit.dto.*;
import omg.wecan.recruit.entity.Recruit;
import omg.wecan.recruit.entity.RecruitComment;
import omg.wecan.recruit.service.RecruitService;
import omg.wecan.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecruitController {
    private final RecruitService recruitService;
    
    @PostMapping("/recruit")
    public Recruit recruitAdd(@AuthenticationPrincipal User loginUser, @RequestBody RecruitInput recruitInput) {
        //토큰으로 유저 인증하고 레포에서 유저 가져와야함
        return recruitService.addRecruit(loginUser, recruitInput);
    }
    
    @PatchMapping("/recruit")
    public Long recruitUpdate(@AuthenticationPrincipal User loginUser, @RequestBody RecruitInput recruitInput) {
        return recruitService.updateRecruit(recruitInput);
    }
    
    @DeleteMapping("/recruit/{id}")
    public Long recruitDelete(@AuthenticationPrincipal User loginUser, @PathVariable Long id) {
        return recruitService.deleteRecruit(id);
    }
    
    @GetMapping("/recruit/{id}")
    public RecruitDetailOutput recruitDetails(@AuthenticationPrincipal User loginUser, @PathVariable Long id) {
        return recruitService.findRecruitDetail(loginUser, id);
    }
    
    @GetMapping("/recruits/home")
    public List<RecruitOutput> recruitFindThree(@AuthenticationPrincipal User loginUser) {
        return recruitService.findThreeRecruit(loginUser);
    }
    
    @GetMapping("/recruits")
    public Page<RecruitOutput> recruitFind(@AuthenticationPrincipal User loginUser, @ModelAttribute RecruitFindCond recruitFindCond, @PageableDefault(size = 4)Pageable pageable) {
        return recruitService.findRecruit(loginUser, recruitFindCond, pageable);
    }

    @PostMapping("/recruit/comment")
    public RecruitComment recruitCommentAdd(@AuthenticationPrincipal User loginUser, @RequestBody CommentAddInput commentAddInput) {
        return recruitService.addRecruitComment(loginUser, commentAddInput);
    }
    
    @PostMapping("/recruit/participation")
    public Long participateAdd(@AuthenticationPrincipal User loginUser, @RequestBody AddParticipateInput addParticipateInput) {
        return recruitService.addParticipate(loginUser, addParticipateInput);
    }

    @DeleteMapping("/recruit/participation")
    public Long participateDelete(@AuthenticationPrincipal User loginUser, @RequestBody DeleteParticipateAndHeartInput deleteParticipateAndHeartInput) {
        return recruitService.deleteParticipate(loginUser, deleteParticipateAndHeartInput);
    }
    
    @PostMapping("/recruit/heart")
    public Long heartAdd(@AuthenticationPrincipal User loginUser, @RequestBody AddHeartInput addHeartInput) {
        return recruitService.addHeart(loginUser, addHeartInput);
    }

    @DeleteMapping("/recruit/heart")
    public Long heartDelete(@AuthenticationPrincipal User loginUser, @RequestBody DeleteParticipateAndHeartInput deleteParticipateAndHeartInput) {
        return recruitService.deleteHeart(loginUser, deleteParticipateAndHeartInput);
    }
}
