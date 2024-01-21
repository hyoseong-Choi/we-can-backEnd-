package omg.wecan.challenge.repository;

import omg.wecan.challenge.entity.Challenge;
import omg.wecan.challenge.entity.UserChallenge;
import omg.wecan.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {
    List<UserChallenge> findByUserUserId(Long userId);

    UserChallenge findByUserAndChallenge(User user, Challenge challenge);

    Optional<UserChallenge> findByChallengeIdAndUser(Long challengeId, User user);

    UserChallenge findByUserAndChallengeId(User user, Long challengeId);
}
