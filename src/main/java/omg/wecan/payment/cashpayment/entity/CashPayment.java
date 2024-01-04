package omg.wecan.payment.cashpayment.entity;

import jakarta.persistence.*;
import lombok.*;
import omg.wecan.global.entity.BaseEntity;
import omg.wecan.payment.cashpayment.dto.response.CashPaymentResponse;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CashPayment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CashPaymentMethod payMethod;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private String orderName;

    @Column(nullable = false)
    private Long totalAmount;

    private String paymentKey;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Boolean isSuccess;

    public CashPaymentResponse toResponse() {
        return CashPaymentResponse.builder()
                .payMethod(payMethod.toString())
                .orderId(orderId)
                .orderName(orderName)
                .totalAmount(totalAmount)
                .userId(userId)
                .createDate(this.getCreatedAt().toString())
                .build();
    }

}
