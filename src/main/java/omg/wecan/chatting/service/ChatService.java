package omg.wecan.chatting.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omg.wecan.chatting.dto.ChatRoom;
import omg.wecan.user.entity.User;
import omg.wecan.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final UserRepository userRepository;
    private Map<String, ChatRoom> chatRoomMap = new LinkedHashMap<>();

    // roomID 기준으로 채팅방 찾기
    public ChatRoom findRoomById(String roomId){
        return chatRoomMap.get(roomId);
    }

    // roomName 로 채팅방 만들기
    public ChatRoom createChatRoom(Long challengeId){
        ChatRoom chatRoom = new ChatRoom().create(challengeId);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);

        return chatRoom;
    }

    // 채팅방 인원+1
    public void plusUserCnt(String roomId){
        ChatRoom room = chatRoomMap.get(roomId);
        room.setUserCount(room.getUserCount()+1);
    }

    // 채팅방 유저 리스트에 유저 추가
    public Long addUser(String roomId, Long userId){
        ChatRoom room = chatRoomMap.get(roomId);
        Optional<User> userOptional = userRepository.findById(userId);

        userOptional.ifPresent(user -> room.getUserList().put(userId, user.getName()));

        return userId;
    }

}
