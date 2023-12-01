package omg.wecan.recruit.repository;

import omg.wecan.recruit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long>, RecruitRepositoryCustom {
    @Transactional(readOnly = true)
    List<Recruit> findTop3ByFinishedOrderByHeartNumDescStartDateDesc(Boolean finished);
    @Transactional(readOnly = true)
    List<Recruit> findByEndDateIs(LocalDate endDate);
}
