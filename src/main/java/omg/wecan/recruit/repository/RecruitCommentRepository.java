package omg.wecan.recruit.repository;

import omg.wecan.recruit.entity.Recruit;
import omg.wecan.recruit.entity.RecruitComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RecruitCommentRepository extends JpaRepository<RecruitComment, Long> {
    @Transactional(readOnly = true)
    @Query("select rc from RecruitComment rc join fetch rc.user where rc.recruit=:recruit")
    List<RecruitComment> findByRecruit(@Param("recruit")Recruit recruit);
    
}
