package omg.wecan.recruit.repository;


import omg.wecan.recruit.entity.Heart;
import omg.wecan.recruit.entity.Recruit;
import omg.wecan.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    @Transactional(readOnly = true)
    Boolean existsByUserAndRecruit(User user, Recruit recruit);
    @Transactional(readOnly = true)
    List<Heart> findAllByUser(User user);
    @Transactional(readOnly = true)
    Optional<Heart> findByUserAndRecruit(User user, Recruit recruit);
}
