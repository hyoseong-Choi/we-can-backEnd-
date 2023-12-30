package omg.wecan.order.dto.request;

import lombok.Getter;
import lombok.Setter;
import omg.wecan.order.entity.Order;
import omg.wecan.order.entity.OrderType;

@Getter
@Setter
public class OrderCreateRequest {
    private String orderName;
    private OrderType type;
    private Long totalCandy;

    public Order toEntity(Long userId){
        return Order.builder()
                .orderName(this.orderName)
                .type(this.type)
                .totalCandy(this.totalCandy)
                .userId(userId)
                .build();
    }
}
