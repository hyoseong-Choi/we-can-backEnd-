package omg.wecan.challenge.repository;

import omg.wecan.challenge.entity.ChallengeCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChallengeCheckRepository extends JpaRepository<ChallengeCheck, Long> {
}

