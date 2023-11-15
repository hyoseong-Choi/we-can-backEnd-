package omg.wecan.recruit.repository;


import omg.wecan.recruit.dto.RecruitFindCond;
import omg.wecan.recruit.entity.Recruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface RecruitRepositoryCustom {
    Page<Recruit> findAllByCond(RecruitFindCond recruitFindCond, Pageable pageable);
    List<Recruit> findAllByCond(RecruitFindCond recruitFindCond);
}
