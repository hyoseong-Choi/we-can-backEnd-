package omg.wecan.recruit.controller;

import lombok.RequiredArgsConstructor;
import omg.wecan.recruit.dto.*;
import omg.wecan.recruit.entity.Heart;
import omg.wecan.recruit.entity.Participate;
import omg.wecan.recruit.entity.Recruit;
import omg.wecan.recruit.service.RecruitService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecruitController {
    private final RecruitService recruitService;
    
    @PostMapping("/recruit")
    public Recruit recruitAdd(@RequestBody RecruitInput recruitInput) {
        //토큰으로 유저 인증하고 레포에서 유저 가져와야함
        return recruitService.addRecruit(recruitInput);
    }
    
    @PatchMapping("/recruit")
    public Recruit recruitUpdate(@RequestBody RecruitInput recruitInput) {
        return recruitService.updateRecruit(recruitInput);
    }
    
    @DeleteMapping("/recruit/{id}")
    public Long recruitDelete(@PathVariable Long id) {
        return recruitService.deleteRecruit(id);
    }
    
    @GetMapping("/recruit/{id}")
    public RecruitDetailOutput recruitDetails(@PathVariable Long id) {
        return recruitService.findRecruitDetail(id);
    }
    
    @GetMapping("/recruits/home")
    public List<RecruitOutput> recruitFindThree() {
        return recruitService.findThreeRecruit();
    }
    
    @GetMapping("/recruits")
    public Page<RecruitOutput> recruitFindRecent(Pageable pageable) {
        return recruitService.findRecentRecruit(pageable);
    }
    
    
    @PostMapping("/recruit/participation")
    public Participate participateAdd(@RequestBody AddParticipateInput addParticipateInput) {
        return recruitService.addParticipate(addParticipateInput);
    }
    
    @PostMapping("/recruit/heart")
    public Heart heartAdd(@RequestBody AddHeartInput addHeartInput) {
        return recruitService.addHeart(addHeartInput);
    }
    
}
