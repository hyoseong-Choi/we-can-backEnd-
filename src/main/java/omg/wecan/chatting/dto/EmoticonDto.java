package omg.wecan.chatting.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmoticonDto {
    private Long userId; // 메시지 타입
    private List<String> emoticonList; // 방 번호
}
