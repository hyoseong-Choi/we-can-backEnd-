package omg.wecan.chatting.dto;

import lombok.*;
import omg.wecan.chatting.entity.ChattingRoom;
import omg.wecan.chatting.entity.ChattingRoomUser;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChattingUserDto {
    private Long userId;
    private String nickName;

    public ChattingUserDto(ChattingRoomUser chattingRoomUser){
        userId = chattingRoomUser.getUser().getUserId();
        nickName = chattingRoomUser.getUser().getNickName();
    }
}
