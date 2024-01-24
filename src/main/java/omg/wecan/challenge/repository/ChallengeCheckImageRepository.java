package omg.wecan.challenge.repository;

import omg.wecan.challenge.entity.ChallengeCheckImage;
import omg.wecan.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeCheckImageRepository extends JpaRepository<ChallengeCheckImage, Long> {

    List<ChallengeCheckImage> findByChallengeCheck_IdAndUser(Long challengeCheckId, User user);
}