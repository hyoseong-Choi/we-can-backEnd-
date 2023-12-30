package omg.wecan.order.service;

import lombok.RequiredArgsConstructor;
import omg.wecan.order.dto.request.OrderCreateRequest;
import omg.wecan.order.dto.response.OrderResponse;
import omg.wecan.order.entity.Order;
import omg.wecan.order.repository.OrderRepository;
import omg.wecan.user.entity.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderResponse save(User user, OrderCreateRequest request) {
        Long userId = user.getUserId();
        Order order = request.toEntity(userId);
        orderRepository.save(order);
        return new OrderResponse(order, user);
    }
}
