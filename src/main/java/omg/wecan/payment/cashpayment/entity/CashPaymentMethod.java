package omg.wecan.payment.cashpayment.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import omg.wecan.charity.entity.CharityCategory;

public enum CashPaymentMethod {
    CARD, VIRTUAL_ACCOUNT;

    @JsonCreator
    public static CashPaymentMethod from(String s){
        return CashPaymentMethod.valueOf(s.toUpperCase());
    }
}
