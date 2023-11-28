package omg.wecan.challenge.repository;

import omg.wecan.challenge.entity.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long> {
}
