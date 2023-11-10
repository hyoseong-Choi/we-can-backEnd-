package omg.wecan.recruit.repository;

import omg.wecan.recruit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long>, RecruitRepositoryCustom {

}
