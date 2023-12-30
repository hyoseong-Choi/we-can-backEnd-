package omg.wecan.payment.cashpayment.dto.request;


import lombok.Data;
import omg.wecan.payment.cashpayment.entity.CashPayment;
import omg.wecan.payment.cashpayment.entity.CashPaymentMethod;

@Data
public class CashPaymentRequest {
    private CashPaymentMethod payMethod;
    private Long totalAmount;
    private String orderName;

    public CashPayment toEntity() {
        return CashPayment.builder()
                .payMethod(payMethod)
                .totalAmount(totalAmount)
                .orderName(orderName)
                .isSuccess(false)
                .build();
    }
}
