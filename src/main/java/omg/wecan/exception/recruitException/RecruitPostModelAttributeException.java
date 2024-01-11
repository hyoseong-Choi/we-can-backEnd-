package omg.wecan.exception.recruitException;

import lombok.Getter;
import omg.wecan.exception.customException.ErrorCode;

@Getter
public class RecruitPostModelAttributeException extends IllegalArgumentException {
    private final ErrorCode code;
    
    public RecruitPostModelAttributeException(ErrorCode code, String message) {
        super(code.getMessage() + "(" + message + ")");
        this.code = code;
    }
    
    public RecruitPostModelAttributeException(ErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }
}