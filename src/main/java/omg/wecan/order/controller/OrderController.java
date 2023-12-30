package omg.wecan.order.controller;

import lombok.RequiredArgsConstructor;
import omg.wecan.auth.presentation.AuthenticationPrincipal;
import omg.wecan.exception.customException.CustomException;
import omg.wecan.exception.customException.ErrorCode;
import omg.wecan.order.dto.request.OrderCreateRequest;
import omg.wecan.order.dto.response.OrderResponse;
import omg.wecan.order.entity.OrderType;
import omg.wecan.order.service.OrderService;
import omg.wecan.user.entity.User;
import omg.wecan.user.service.UserService;
import omg.wecan.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(
            @AuthenticationPrincipal User user,
            @RequestBody OrderCreateRequest request
    ) {
        long userId = user.getUserId();
        long userCandy = user.getCandy();
        long orderCandy = request.getTotalCandy();
        OrderType orderType = request.getType();

        if (userCandy < orderCandy) {
            throw new CustomException(ErrorCode.REJECT_PAYMENT, "user candy: " + userCandy);
        }

        if(orderType == OrderType.DONATION)
            donationLogic();
        else if(orderType == OrderType.ITEM)
            itemLogic();
        else if(orderType == OrderType.FINE)
            fineLogic();
        else{
            throw new CustomException(ErrorCode.ORDER_TYPE_INVALID);
        }

        userService.addCandy(userId, -orderCandy);
        orderService.save(user, request);
        OrderResponse response = new OrderResponse(userId, user.getName(), request.getOrderName(), user.getCandy());
        return ResponseEntity.ok().body(ApiResponse.success(response));
    }

    private void donationLogic() {

    }

    private void itemLogic() {

    }

    private void fineLogic(){

    }
}
