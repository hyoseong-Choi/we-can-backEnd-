package omg.wecan.chatting.dto;

import lombok.*;
import omg.wecan.chatting.Enum.MessageType;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {

    private MessageType type; // 메시지 타입
    private String roomId; // 방 번호
    private Long userId; // 채팅 보낸 사람
    private String userName; // 채팅 보낸 사람 이름
    private String message; // 메시지
    private String time; // 채팅 발송 시간
}
