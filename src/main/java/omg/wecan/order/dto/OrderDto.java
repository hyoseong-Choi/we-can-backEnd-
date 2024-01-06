package omg.wecan.order.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import omg.wecan.order.entity.Order;
import omg.wecan.order.entity.OrderType;

@Getter
@Setter
public class OrderDto {
    private String orderName;
    private OrderType type;
    private Long totalCandy;
    private Long objId;

    public Order toEntity(Long userId){
        return Order.builder()
                .orderName(this.orderName)
                .type(this.type)
                .totalCandy(this.totalCandy)
                .userId(userId)
                .objId(this.objId)
                .build();
    }
}
