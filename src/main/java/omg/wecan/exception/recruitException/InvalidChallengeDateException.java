package omg.wecan.exception.recruitException;

import lombok.Getter;
import omg.wecan.exception.customException.ErrorCode;

@Getter
public class InvalidChallengeDateException extends IllegalArgumentException {
    private final ErrorCode code;
    
    public InvalidChallengeDateException(ErrorCode code, String message){
        super(code.getMessage() + "("+message + ")");
        this.code = code ;
    }
    public InvalidChallengeDateException(ErrorCode code){
        super(code.getMessage());
        this.code = code;
    }
}
