package omg.wecan.recruit.repository;


import omg.wecan.recruit.entity.Heart;
import omg.wecan.recruit.entity.Recruit;
import omg.wecan.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByUserAndRecruit(User user, Recruit recruit);
}
