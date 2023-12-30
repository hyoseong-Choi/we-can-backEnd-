package omg.wecan.exception.shopException;

import lombok.Getter;
import omg.wecan.exception.customException.ErrorCode;
@Getter
public class NoUserItemException extends NullPointerException {
    private final ErrorCode code;
    
    public NoUserItemException(ErrorCode code, String message){
        super(code.getMessage() + "("+message + ")");
        this.code = code ;
    }
    
    public NoUserItemException(ErrorCode code){
        super(code.getMessage());
        this.code = code;
    }
}
