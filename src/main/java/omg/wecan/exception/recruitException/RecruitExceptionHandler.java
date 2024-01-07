package omg.wecan.exception.recruitException;

import omg.wecan.exception.customException.ErrorResponse;
import omg.wecan.recruit.controller.RecruitController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static omg.wecan.exception.customException.ErrorCode.RECRUIT_ATTRIBUTE_INVALID;

@RestControllerAdvice(assignableTypes = RecruitController.class)
public class RecruitExceptionHandler {
    
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> InvalidChallengeDateException(InvalidChallengeDateException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(e.getCode().getCode())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(e.getCode().getStatus())
                .body(errorResponse);
    }
    
    @InitBinder
    public void handleRecruitPostModelAttributeException(WebDataBinder binder) {
        if (binder.getBindingResult().hasErrors()) {
            throw new RecruitPostModelAttributeException(RECRUIT_ATTRIBUTE_INVALID);
        }
    }
    
    public ResponseEntity<ErrorResponse> RecruitPostModelAttributeException(RecruitPostModelAttributeException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(e.getCode().getCode())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(e.getCode().getStatus())
                .body(errorResponse);
    }
}
