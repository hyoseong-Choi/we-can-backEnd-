package omg.wecan.recruit.repository;


import omg.wecan.recruit.dto.RecruitFindCond;
import omg.wecan.recruit.entity.Recruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface RecruitRepositoryCustom {
    Page<Recruit> findAllByCond(RecruitFindCond recruitFindCond, Pageable pageable);
}
