package omg.wecan.chatting.repository;

import omg.wecan.chatting.entity.ChattingRoomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChattingRoomUserRepository extends JpaRepository<ChattingRoomUser, Long> {

    Optional<ChattingRoomUser> findByChattingRoomIdAndUserUserId(Long roomId, Long userId);

    List<ChattingRoomUser> findByChattingRoomId(Long roomId);

    Optional<ChattingRoomUser> findByUserUserId(Long userId);
}
