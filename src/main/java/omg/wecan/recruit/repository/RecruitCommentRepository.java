package omg.wecan.recruit.repository;

import omg.wecan.recruit.entity.Recruit;
import omg.wecan.recruit.entity.RecruitComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RecruitCommentRepository extends JpaRepository<RecruitComment, Long> {
    @Transactional(readOnly = true)
    List<RecruitComment> findByRecruit(Recruit recruit);
}
