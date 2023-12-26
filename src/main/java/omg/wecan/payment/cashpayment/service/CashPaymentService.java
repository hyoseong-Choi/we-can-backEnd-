package omg.wecan.payment.cashpayment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omg.wecan.exception.customException.CustomException;
import omg.wecan.exception.customException.ErrorCode;
import omg.wecan.payment.cashpayment.dto.request.CashPaymentRequest;
import omg.wecan.payment.cashpayment.dto.response.CashPaymentResponse;
import omg.wecan.payment.cashpayment.entity.CashPayment;
import omg.wecan.payment.cashpayment.repository.CashPaymentRepository;
import omg.wecan.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CashPaymentService {
    private final CashPaymentRepository cashPaymentRepository;

    @Value("${payments.toss.client_key}")
    private String clientKey;

    @Value("${payments.toss.secret_key}")
    private String secretKey;

    public CashPayment save(CashPaymentRequest request, Long userId) {
        CashPayment cashPayment = request.toEntity();
        cashPayment.setOrderId(UUID.randomUUID().toString());
        cashPayment.setUserId(userId);

        return cashPaymentRepository.save(cashPayment);
    }

    public void setPaymentKey(String orderId, String paymentKey) {
        CashPayment payment = cashPaymentRepository.getByOrderId(orderId);
        payment.setPaymentKey(paymentKey);
    }

    public void setSuccess(String orderId) {
        CashPayment payment = cashPaymentRepository.getByOrderId(orderId);
        payment.setIsSuccess(true);
    }

    public CashPayment findByOrderId(String orderId){
        return cashPaymentRepository.getByOrderId(orderId);
    }
    @Transactional(readOnly = true)
    public void validatePaymentResult(String orderId, Long amount) {
        CashPayment payment = cashPaymentRepository.getByOrderId(orderId);

        if(!amount.equals(payment.getTotalAmount())){
            throw new CustomException(ErrorCode.ORDER_AMOUNT_MISMATCH);
        }
    }
}
