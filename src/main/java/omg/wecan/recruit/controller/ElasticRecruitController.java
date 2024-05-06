package omg.wecan.recruit.controller;

import lombok.RequiredArgsConstructor;
import omg.wecan.auth.presentation.AuthenticationPrincipal;
import omg.wecan.recruit.dto.RecruitFindCond;
import omg.wecan.recruit.dto.RecruitOutput;
import omg.wecan.recruit.service.ElasticRecruitService;
import omg.wecan.user.entity.User;
import omg.wecan.util.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ElasticRecruitController {
    private final ElasticRecruitService elasticRecruitService;
    
    @GetMapping("/recruits/search")
    public ResponseEntity<ApiResponse<Page<RecruitOutput>>> recruitFind(@AuthenticationPrincipal(required = false) User loginUser,
                                                                        @ModelAttribute RecruitFindCond recruitFindCond, @PageableDefault(size = 12) Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(elasticRecruitService.findRecruit(recruitFindCond, pageable)));
    }
    
}
