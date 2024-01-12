package omg.wecan.chatting.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
public class ChatRoom {
    private String roomId; // 채팅방 아이디
    private Long challengeId; // 해당 챌린지 id
    private int userCount; // 채팅방 인원수
    private HashMap<Long, String> userList = new HashMap<Long, String>();

    public ChatRoom create(Long challengeId){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.challengeId = challengeId;
        chatRoom.userCount = 0;

        return chatRoom;
    }

}
