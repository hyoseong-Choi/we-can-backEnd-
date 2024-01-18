package omg.wecan.chatting.repository;

import omg.wecan.chatting.entity.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long> {

    Optional<ChattingRoom> findFirstByChallengeId(Long challengeId);
}
