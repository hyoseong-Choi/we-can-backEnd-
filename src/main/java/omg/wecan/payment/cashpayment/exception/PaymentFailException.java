package omg.wecan.payment.cashpayment.exception;

public class PaymentFailException extends RuntimeException{
    public PaymentFailException(final String message){
        super(message);
    }
    public PaymentFailException(){
        this("결제 실패했습니다.");
    }
}