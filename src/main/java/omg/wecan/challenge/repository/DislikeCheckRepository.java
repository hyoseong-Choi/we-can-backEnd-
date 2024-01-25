package omg.wecan.challenge.repository;

import omg.wecan.challenge.entity.DislikeCheck;
import omg.wecan.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DislikeCheckRepository extends JpaRepository<DislikeCheck, Long> {

    DislikeCheck findByUserAndChallengeCheckId(User user, Long challengeCheckId);
}
