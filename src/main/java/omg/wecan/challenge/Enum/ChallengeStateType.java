package omg.wecan.challenge.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ChallengeStateType {//미라킁모닝 운동 공부 기타
    Upcoming, Active, Completed;
    
    @JsonCreator
    public static ChallengeStateType from(String type){
        return ChallengeStateType.valueOf(type.toUpperCase());
    }
}
