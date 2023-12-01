package omg.wecan.exception.customException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    //Challenge
    CHALLENGE_NOT_FOUND(HttpStatus.NOT_FOUND,"1001", "해당 챌린지를 찾을 수 없습니다."),

    //Review
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND,"2001", "해당 리뷰 존재하지 않습니다."),
    REVIEW_AUTHOR_MISMATCH(HttpStatus.FORBIDDEN,"2002", "리뷰 작성자와 로그인한 사용자가 일치하지 않습니다."),
    CHALLENGE_AUTHOR_MISMATCH(HttpStatus.BAD_REQUEST,"2003", "기존 리뷰의 challengeId와 요청한 challengeId가 일치하지 않습니다."),

    //charity
    CHARITY_NOT_FOUND(HttpStatus.NOT_FOUND,"3404", "해당 기부 단체는 존재하지 않습니다."),

    //DonationCertificate
    DONATIONCERTIFICATE_NOT_FOUND(HttpStatus.NOT_FOUND, "4404", "해당 기부 증서는 존재하지 않습니다."),

    //user
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "6404", "해당 사용자가 존재하지 않습니다.");
    USER_EMAIL_MISMATCH(HttpStatus.BAD_REQUEST,"6400", "해당 이메일을 가진 사용자가 없습니다."),
    USER_NAME_EMAIL_MISMATCH(HttpStatus.BAD_REQUEST,"6400", "해당 이메일, 이름을 가진 사용자가 없습니다."),
    
    //recruit
    OTP_MISMATCH(HttpStatus.BAD_REQUEST,"7400", "해당 이메일을 가진 사용자가 없습니다."),
    OTP_NOT_FOUND(HttpStatus.NOT_FOUND,"7404", "해당 이메일을 가진 사용자가 없습니다.");
    
    

    private final HttpStatus status;
    private final String code;
    private final String message;
}