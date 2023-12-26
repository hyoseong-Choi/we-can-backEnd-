package omg.wecan.payment.cashpayment.repository;

import omg.wecan.exception.customException.CustomException;
import omg.wecan.exception.customException.ErrorCode;
import omg.wecan.payment.cashpayment.entity.CashPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CashPaymentRepository extends JpaRepository<CashPayment, Long> {
    default CashPayment getByOrderId(String orderId){

        return this.findByOrderId(orderId)
                        .orElseThrow(()->new CustomException(
                                        ErrorCode.ORDER_NOT_FOUND,
                                        "order id: " + orderId));
    }

    public Optional<CashPayment> findByOrderId(String orderId);

}
