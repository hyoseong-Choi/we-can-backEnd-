package omg.wecan.order.entity;

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
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private String orderName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderType type;

    @Column(nullable = false)
    private Long totalCandy;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long objId; //itemId or challengeId (UserChallengeId면 안됨)
}
