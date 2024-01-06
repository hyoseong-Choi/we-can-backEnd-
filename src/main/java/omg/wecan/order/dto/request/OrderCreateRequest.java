package omg.wecan.order.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import omg.wecan.order.entity.Order;
import omg.wecan.order.entity.OrderType;

@Getter
@Setter
public class OrderCreateRequest {
    @NotNull
    private String orderName;
    @NotNull
    private OrderType type;
    @NotNull
    private Long totalCandy;
    @NotNull
    private Long objId;

    public Order toEntity(Long userId){
        return Order.builder()
                .orderName(this.orderName)
                .type(this.type)
                .totalCandy(this.totalCandy)
                .userId(userId)
                .build();
    }
}
