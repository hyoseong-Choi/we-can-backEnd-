package omg.wecan.recruit.repository;


import omg.wecan.recruit.entity.Heart;
import omg.wecan.recruit.entity.Recruit;
import omg.wecan.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Boolean existsByUserAndRecruit(User user, Recruit recruit);
    Boolean existsByUser(User user);
    List<Heart> findAllByUser(User user);
}
