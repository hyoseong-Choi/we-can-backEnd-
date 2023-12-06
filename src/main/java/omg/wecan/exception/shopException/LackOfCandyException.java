package omg.wecan.exception.shopException;

import lombok.Getter;
import omg.wecan.exception.customException.ErrorCode;
@Getter
public class LackOfCandyException extends RuntimeException {
    private final ErrorCode code;
    
    public LackOfCandyException(ErrorCode code, String message){
        super(code.getMessage() + "("+message + ")");
        this.code = code ;
    }
    public LackOfCandyException(ErrorCode code){
        super(code.getMessage());
        this.code = code;
    }
}
