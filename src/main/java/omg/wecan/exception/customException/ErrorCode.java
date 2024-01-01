package omg.wecan.exception.customException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    //Challenge
    CHALLENGE_NOT_FOUND(HttpStatus.NOT_FOUND,"1404", "해당 챌린지를 찾을 수 없습니다."),
    CHALLENGE_CHECK_NOT_FOUND(HttpStatus.NOT_FOUND,"1404", "해당 챌린지 인증을 찾을 수 없습니다."),

    //Review
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND,"2404", "해당 리뷰 존재하지 않습니다."),
    REVIEW_AUTHOR_MISMATCH(HttpStatus.FORBIDDEN,"2400", "리뷰 작성자와 로그인한 사용자가 일치하지 않습니다."),
    CHALLENGE_AUTHOR_MISMATCH(HttpStatus.BAD_REQUEST,"2400", "기존 리뷰의 challengeId와 요청한 challengeId가 일치하지 않습니다."),

    //Recruit
    RECRUIT_DATE_INVALID(HttpStatus.BAD_REQUEST,"5003", "챌린지 날짜가 유효하지 않습니다."),

    //charity
    CHARITY_NOT_FOUND(HttpStatus.NOT_FOUND,"3404", "해당 기부 단체는 존재하지 않습니다."),

    //DonationCertificate
    DONATIONCERTIFICATE_NOT_FOUND(HttpStatus.NOT_FOUND, "4404", "해당 기부 증서는 존재하지 않습니다."),

    //user
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "6404", "해당 사용자가 존재하지 않습니다."),
    USER_EMAIL_MISMATCH(HttpStatus.BAD_REQUEST,"6400", "해당 이메일을 가진 사용자가 없습니다."),
    USER_NAME_EMAIL_MISMATCH(HttpStatus.BAD_REQUEST,"6400", "해당 이메일, 이름을 가진 사용자가 없습니다."),
    OTP_MISMATCH(HttpStatus.BAD_REQUEST,"6400", "인증 번호가 틀렸습니다."),
    OTP_NOT_FOUND(HttpStatus.NOT_FOUND,"6404", "인증 번호가 만료되었습니다."),
    
    //Shop
    USERITEM_NOT_FOUND(HttpStatus.NOT_FOUND,"7404", "구매한 아이템이 없습니다."),
    REJECT_PAYMENT(HttpStatus.FORBIDDEN,"7403", "캔디가 부족합니다."),
    
    //payment
    ORDER_NOT_FOUND(HttpStatus.BAD_REQUEST,"8404", "해당 주문내역이 없습니다."),
    ORDER_AMOUNT_MISMATCH(HttpStatus.BAD_REQUEST,"8400", "주문 금액이 다릅니다."),

    //toss
    TOSS_FAIL(HttpStatus.BAD_REQUEST, "9400", "토스 요청에 실패했습니다"),

    //order
    ORDER_TYPE_INVALID(HttpStatus.BAD_REQUEST, "10400", "유효하지 않은 order type입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
