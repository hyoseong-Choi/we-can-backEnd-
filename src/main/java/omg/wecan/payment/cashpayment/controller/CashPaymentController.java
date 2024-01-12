package omg.wecan.payment.cashpayment.controller;

import lombok.RequiredArgsConstructor;
import omg.wecan.auth.presentation.AuthenticationPrincipal;
import omg.wecan.payment.cashpayment.client.TossPaymentClient;
import omg.wecan.payment.cashpayment.dto.request.CashPaymentRequest;
import omg.wecan.payment.cashpayment.dto.response.CashPaymentResponse;
import omg.wecan.payment.cashpayment.dto.response.CashPaymentResultResponse;
import omg.wecan.payment.cashpayment.entity.CashPayment;
import omg.wecan.payment.cashpayment.service.CashPaymentService;
import omg.wecan.user.entity.User;
import omg.wecan.user.service.UserService;
import omg.wecan.util.ApiResponse;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/payment/toss")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CashPaymentController {
    private final CashPaymentService cashPaymentService;
    private final UserService userService;
    private final TossPaymentClient tossPaymentClient;
    @Value("${payments.toss.success_url}")
    private String TOSS_SUCCESS_URL;
    @Value("${payments.toss.fail_url}")
    private String TOSS_FAIL_URL;

    @PostMapping()
    public ResponseEntity<ApiResponse<CashPaymentResponse>> convertData(
            @AuthenticationPrincipal User user,
            @RequestBody CashPaymentRequest request) {

        CashPayment saved = cashPaymentService.save(request, user.getUserId());
        CashPaymentResponse innerResponse = saved.toResponse();
        innerResponse.setSuccessUrl(TOSS_SUCCESS_URL);
        innerResponse.setFailUrl(TOSS_FAIL_URL);

        return ResponseEntity.ok().body(ApiResponse.success(innerResponse));
    }

    @GetMapping("/success")
    public ResponseEntity<ApiResponse<CashPaymentResultResponse>> paymentSuccess(@RequestParam String paymentKey,
                                               @RequestParam String orderId,
                                               @RequestParam Long amount) throws JSONException, IOException, ParseException {
        long purchaseCandyCnt;
        CashPayment payment;

        cashPaymentService.validatePaymentResult(orderId, amount);
        cashPaymentService.setPaymentKey(orderId, paymentKey);
        cashPaymentService.setSuccess(orderId);

        CashPaymentResultResponse innerResponse = tossPaymentClient.fetch(paymentKey, orderId, amount);
        purchaseCandyCnt = Integer.parseInt(innerResponse.getOrderName().split(" ")[1]);
        payment = cashPaymentService.findByOrderId(orderId);
        userService.addCandy(payment.getUserId(), purchaseCandyCnt);

        return ResponseEntity.ok(ApiResponse.success(innerResponse));
    }

    @GetMapping("/fail")
    public ResponseEntity<ApiResponse<Void>> paymentFail(
            @RequestParam String code,
            @RequestParam String message,
            @RequestParam String orderId
    ){
        ApiResponse response = new ApiResponse(false, code, message, orderId);

        return ResponseEntity.ok().body(
                response
        );
    }
}
