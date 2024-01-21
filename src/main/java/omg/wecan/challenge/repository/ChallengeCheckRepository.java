package omg.wecan.challenge.repository;

import io.lettuce.core.dynamic.annotation.Param;
import omg.wecan.challenge.entity.ChallengeCheck;
import omg.wecan.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChallengeCheckRepository extends JpaRepository<ChallengeCheck, Long> {
    List<ChallengeCheck> findByChallengeId(Long challengeId);

    Optional<ChallengeCheck> findByChallengeIdAndUser(Long challengeId, User user);
}

