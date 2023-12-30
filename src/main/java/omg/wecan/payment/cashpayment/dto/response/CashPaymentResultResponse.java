package omg.wecan.payment.cashpayment.dto.response;

import lombok.Data;
import omg.wecan.payment.cashpayment.dto.CardDto;

@Data
public class CashPaymentResultResponse {
    String paymentKey;
    String orderId;
    String orderName;
    String status;
    String requestedAt;
    String approvedAt;
    String cultureExpense;
    CardDto card;
}
