package omg.wecan.recruit.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PaymentType {
    TEAM, PERSONAL;
    
    @JsonCreator
    public static PaymentType from(String type){
        return PaymentType.valueOf(type.toUpperCase());
    }

    
}
