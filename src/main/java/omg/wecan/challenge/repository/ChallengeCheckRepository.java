package omg.wecan.challenge.repository;

import io.lettuce.core.dynamic.annotation.Param;
import omg.wecan.challenge.entity.ChallengeCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChallengeCheckRepository extends JpaRepository<ChallengeCheck, Long> {
    List<ChallengeCheck> findByChallengeId(Long challengeId);
}

