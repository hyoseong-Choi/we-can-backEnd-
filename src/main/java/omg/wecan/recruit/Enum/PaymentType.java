package omg.wecan.recruit.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PaymentType {
    TEAM, PERSONAL;
    
    @JsonCreator
    public static PaymentType from(String type){
        return PaymentType.valueOf(type.toUpperCase());
    }
    
//    @JsonCreator
//    public static PaymentType from(String type) {
//        for (PaymentType paymentType : PaymentType.values()) {
//            if (paymentType.getType().equals(type)) {
//                return paymentType;
//            }
//        }
//        return null;
//    }
//
//    @JsonValue
//    public String getType() {
//        return type;
//    }
    
}
