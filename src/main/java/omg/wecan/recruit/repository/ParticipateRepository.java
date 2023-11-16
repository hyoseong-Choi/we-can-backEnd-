package omg.wecan.recruit.repository;

import omg.wecan.recruit.entity.Participate;
import omg.wecan.recruit.entity.Recruit;
import omg.wecan.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipateRepository extends JpaRepository<Participate, Long> {
    Long countByRecruit(Recruit recruit);

    Boolean existsByUserAndRecruit(User user, Recruit recruit);
}
