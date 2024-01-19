package omg.wecan.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import omg.wecan.challenge.entity.Challenge;
import omg.wecan.chatting.dto.ChatDto;
import omg.wecan.chatting.dto.ChattingUserDto;
import omg.wecan.chatting.entity.ChattingRoomUser;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChallengeInfoDto {
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String  successRate;
    private Long chattingRoomId;
    private List<ChatDto> chattingList;

    public ChallengeInfoDto(Challenge challenge, int successRate, Long chattingRoomId, List<ChatDto> chatDtoList){
        this.title = challenge.getTitle();
        this.startDate = challenge.getStartDate();
        this.endDate = challenge.getEndDate();
        this.successRate = successRate + "%";
        this.chattingRoomId = chattingRoomId;
        this.chattingList = chatDtoList;

    }
}