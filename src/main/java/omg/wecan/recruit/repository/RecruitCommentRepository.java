package omg.wecan.recruit.repository;

import omg.wecan.recruit.entity.Recruit;
import omg.wecan.recruit.entity.RecruitComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitCommentRepository extends JpaRepository<RecruitComment, Long> {
    List<RecruitComment> findByRecruit(Recruit recruit);
}
