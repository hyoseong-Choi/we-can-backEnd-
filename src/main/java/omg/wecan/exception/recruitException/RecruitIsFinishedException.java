package omg.wecan.exception.recruitException;

import lombok.Getter;
import omg.wecan.exception.customException.ErrorCode;

@Getter
public class RecruitIsFinishedException extends RuntimeException {
    private final ErrorCode code;

    public RecruitIsFinishedException(ErrorCode code, String message){
        super(code.getMessage() + "("+message + ")");
        this.code = code ;
    }
    public RecruitIsFinishedException(ErrorCode code){
        super(code.getMessage());
        this.code = code;
    }
}
