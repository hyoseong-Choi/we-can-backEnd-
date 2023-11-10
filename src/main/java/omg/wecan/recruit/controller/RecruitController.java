package omg.wecan.recruit.controller;

import lombok.RequiredArgsConstructor;
import omg.wecan.recruit.dto.AddRecruitInput;
import omg.wecan.recruit.service.RecruitService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecruitController {
    private final RecruitService recruitService;
    
    @PostMapping("/recruit")
    public AddRecruitInput recruitAdd(@RequestBody AddRecruitInput addRecruitInput) {
        //토큰으로 유저 인증하고 레포에서 유저 가져와야함
        recruitService.addRecruit(addRecruitInput);
        return addRecruitInput;
    }
}
