package omg.wecan.challenge.repository;

import omg.wecan.challenge.entity.Challenge;
import omg.wecan.recruit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    List<Challenge> findByEndDateIs(LocalDate now);
}

