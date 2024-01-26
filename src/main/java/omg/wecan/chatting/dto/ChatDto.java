package omg.wecan.chatting.dto;

import lombok.*;
import omg.wecan.chatting.Enum.MessageType;
import omg.wecan.chatting.entity.Chatting;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {

    private MessageType type; // 메시지 타입
    private Long roomId; // 방 번호
    private Long userId; // 채팅 보낸 사람
    private String nickName; // 채팅 보낸 사람 이름
    private String message; // 메시지
    private String time; // 채팅 발송 시간

    public static ChatDto create(Chatting chatting) {
        ChatDto chatDto = new ChatDto();
        chatDto.type = MessageType.TALK;
        chatDto.roomId = chatting.getChattingRoom().getId();
        chatDto.userId = chatting.getChattingRoomUser().getUser().getUserId();
        chatDto.nickName = chatting.getChattingRoomUser().getUser().getNickName();
        chatDto.message = chatting.getMessage();
        chatDto.time = chatting.getCreatedAt().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        );

        return chatDto;
    }
}
