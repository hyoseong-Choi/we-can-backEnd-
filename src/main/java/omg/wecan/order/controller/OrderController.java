package omg.wecan.order.controller;

import lombok.RequiredArgsConstructor;
import omg.wecan.auth.presentation.AuthenticationPrincipal;
import omg.wecan.challenge.service.ChallengeService;
import omg.wecan.exception.customException.CustomException;
import omg.wecan.exception.customException.ErrorCode;
import omg.wecan.exception.shopException.LackOfCandyException;
import omg.wecan.order.dto.OrderDto;
import omg.wecan.order.dto.request.OrderCreateRequest;
import omg.wecan.order.dto.response.OrderResponse;
import omg.wecan.order.entity.OrderType;
import omg.wecan.order.service.OrderService;
import omg.wecan.shop.entity.Item;
import omg.wecan.shop.entity.UserItem;
import omg.wecan.shop.service.ItemService;
import omg.wecan.user.entity.User;
import omg.wecan.user.service.UserService;
import omg.wecan.util.ApiResponse;
import omg.wecan.util.event.BuyItemEvent;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static omg.wecan.exception.customException.ErrorCode.REJECT_PAYMENT;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {
    private final ModelMapper mapper;
    private final OrderService orderService;


    @PostMapping()
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(
            @AuthenticationPrincipal User user,
            @RequestBody OrderCreateRequest request
    ) {
        OrderDto orderDto = mapper.map(request, OrderDto.class);
        OrderResponse response = orderService.processOrder(user.getUserId(), orderDto);
        return ResponseEntity.ok().body(ApiResponse.success(response));
    }
}
