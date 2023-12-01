package omg.wecan.exception.customException;

import lombok.Getter;

import java.util.NoSuchElementException;
@Getter
public class WrongOtpException extends NoSuchElementException {
    private final ErrorCode code;
    
    public WrongOtpException(ErrorCode code, String message){
        super(code.getMessage() + "("+message + ")");
        this.code = code ;
    }
    
    public WrongOtpException(ErrorCode code){
        super(code.getMessage());
        this.code = code;
    }
}
