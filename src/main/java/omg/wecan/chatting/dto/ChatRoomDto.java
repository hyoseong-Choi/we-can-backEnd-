package omg.wecan.chatting.dto;

import lombok.Getter;
import lombok.Setter;
import omg.wecan.chatting.entity.ChattingRoom;

import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
public class ChatRoomDto {
    private Long roomId; // 채팅방 아이디
    private Long challengeId; // 해당 챌린지 id
    private Long userCount; // 채팅방 인원수

    public ChatRoomDto(ChattingRoom chattingRoom){
        roomId = chattingRoom.getId();
        challengeId = chattingRoom.getChallenge().getId();
        userCount = chattingRoom.getUserCount();
    }

}
