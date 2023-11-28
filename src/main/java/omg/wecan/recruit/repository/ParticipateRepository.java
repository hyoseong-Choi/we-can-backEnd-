package omg.wecan.recruit.repository;

import omg.wecan.recruit.entity.Participate;
import omg.wecan.recruit.entity.Recruit;
import omg.wecan.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ParticipateRepository extends JpaRepository<Participate, Long> {
    @Transactional(readOnly = true)
    Long countByRecruit(Recruit recruit);
    @Transactional(readOnly = true)
    Boolean existsByUserAndRecruit(User user, Recruit recruit);
    @Transactional
    void deleteByUserAndRecruit(User user, Recruit recruit);
    @Transactional(readOnly = true)
    List<Participate> findByRecruit(Recruit recruit);
}
