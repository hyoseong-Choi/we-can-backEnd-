package omg.wecan.payment.cashpayment.dto.response;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CashPaymentResponse {
    private String payMethod;
    private String orderId;
    private String orderName;
    private Long totalAmount;
    private String successUrl;
    private String failUrl;
    private String createDate;
    private Long userId;
}
