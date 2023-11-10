package omg.wecan.recruit.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ChallengeType {//미라킁모닝 운동 공부 기타
    MIRACLE_MORNING, HEALTH, STUDY, ETC;
    
    @JsonCreator
    public static ChallengeType from(String type){
        return ChallengeType.valueOf(type.toUpperCase());
    }
}
