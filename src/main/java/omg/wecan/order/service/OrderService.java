package omg.wecan.order.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import omg.wecan.challenge.entity.UserChallenge;
import omg.wecan.challenge.repository.UserChallengeRepository;
import omg.wecan.challenge.service.ChallengeService;
import omg.wecan.exception.customException.CustomException;
import omg.wecan.exception.customException.ErrorCode;
import omg.wecan.order.dto.OrderDto;
import omg.wecan.order.dto.request.OrderCreateRequest;
import omg.wecan.order.dto.response.OrderResponse;
import omg.wecan.order.entity.Order;
import omg.wecan.order.entity.OrderType;
import omg.wecan.order.repository.OrderRepository;
import omg.wecan.shop.entity.Item;
import omg.wecan.shop.service.ItemService;
import omg.wecan.user.entity.User;
import omg.wecan.user.repository.UserRepository;
import omg.wecan.util.event.BuyItemEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final UserChallengeRepository userChallengeRepository;
    private final ItemService itemService;
    private final ApplicationEventPublisher eventPublisher;

    public OrderResponse processOrder(Long userId, OrderDto orderDto) {
        User user = userRepository.findById(userId).get();
        long userCandy = user.getCandy();
        long orderCandy = orderDto.getTotalCandy();

        checkCandy(userCandy, orderCandy);
        callProperLogic(user, orderDto);

        Order order = orderDto.toEntity(userId);
        orderRepository.save(order);
        //eventPublish();

        return new OrderResponse(order, user);
    }

    private void checkCandy(long userCandy, long orderCandy) {
        if (userCandy < orderCandy) {
            throw new CustomException(ErrorCode.REJECT_PAYMENT, "user candy: " + userCandy);
        }
    }

    private void callProperLogic(User user, OrderDto orderDto) {
        long userCandy = user.getCandy();
        long orderCandy = orderDto.getTotalCandy();
        OrderType orderType = orderDto.getType();
        long orderObjId = orderDto.getObjId();

        if (orderType == OrderType.DONATION)
            challengeLogic(user, orderObjId);
        else if (orderType == OrderType.ITEM)
            itemLogic(user, orderObjId);
        else if (orderType == OrderType.FINE)
            fineLogic();
        else {
            throw new CustomException(ErrorCode.ORDER_TYPE_INVALID);
        }

        user.setCandy(userCandy - orderCandy);
    }

    private void itemLogic(User user, Long itemId) {
        itemService.buyItemV2(user, itemId);
    }

    private void challengeLogic(User user, Long challengeId) {
        UserChallenge userChallenge = userChallengeRepository.findByChallengeIdAndUser(challengeId, user)
                .orElseThrow(() -> new CustomException(ErrorCode.CHALLENGE_NOT_FOUND));

        userChallenge.setPayed(true);
    }

    private void fineLogic() {

    }
    private void eventPublish(OrderType orderType) {

    }
}
