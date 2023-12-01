package omg.wecan.exception.customException;

import lombok.Getter;

import java.util.NoSuchElementException;
@Getter
public class NoUserWithNameAndEmailException extends NoSuchElementException {
    private final ErrorCode code;
    
    public NoUserWithNameAndEmailException(ErrorCode code, String message){
        super(code.getMessage() + "("+message + ")");
        this.code = code ;
    }
    
    public NoUserWithNameAndEmailException(ErrorCode code){
        super(code.getMessage());
        this.code = code;
    }
}
