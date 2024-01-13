package omg.wecan.chatting.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MessageType {//미라킁모닝 운동 공부 기타
    ENTER, TALK, LEAVE;

    @JsonCreator
    public static omg.wecan.chatting.Enum.MessageType from(String type){
        return omg.wecan.chatting.Enum.MessageType.valueOf(type.toUpperCase());
    }
}
