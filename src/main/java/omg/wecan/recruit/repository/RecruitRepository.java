package omg.wecan.recruit.repository;

import omg.wecan.recruit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long>, RecruitRepositoryCustom {
    List<Recruit> findTop3ByOrderByHeartNumDesc();
}
