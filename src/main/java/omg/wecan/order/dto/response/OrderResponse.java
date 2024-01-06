package omg.wecan.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import omg.wecan.order.entity.Order;
import omg.wecan.user.entity.User;

@Getter
@AllArgsConstructor
public class OrderResponse {
    private Long orderId;
    private String userName;
    private String orderName;
    private Long currentUserCandy;

    public OrderResponse(Order order, User user){
        this.orderId = order.getOrderId();
        this.userName = user.getName();
        this.orderName = order.getOrderName();
        this.currentUserCandy = user.getCandy();
    }
}
