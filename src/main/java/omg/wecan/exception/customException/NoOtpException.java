package omg.wecan.exception.customException;

import lombok.Getter;

import java.util.NoSuchElementException;
@Getter
public class NoOtpException extends NoSuchElementException {
    private final ErrorCode code;
    
    public NoOtpException(ErrorCode code, String message){
        super(code.getMessage() + "("+message + ")");
        this.code = code ;
    }
    
    public NoOtpException(ErrorCode code){
        super(code.getMessage());
        this.code = code;
    }
}
