package omg.wecan.chatting.repository;

import omg.wecan.chatting.entity.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChattingRepository extends JpaRepository<Chatting, Long> {

    List<Chatting> findByChattingRoomId(Long chattingRoomId);
}
