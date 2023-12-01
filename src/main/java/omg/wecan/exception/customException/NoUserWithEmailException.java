package omg.wecan.exception.customException;

import lombok.Getter;

import java.util.NoSuchElementException;
@Getter
public class NoUserWithEmailException extends NoSuchElementException {
    
    private final ErrorCode code;
    
    public NoUserWithEmailException(ErrorCode code, String message){
        super(code.getMessage() + "("+message + ")");
        this.code = code ;
    }
    
    public NoUserWithEmailException(ErrorCode code){
        super(code.getMessage());
        this.code = code;
    }
}
