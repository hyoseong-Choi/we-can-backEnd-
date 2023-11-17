package omg.wecan.exception;

import omg.wecan.user.controller.UserController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = UserController.class)
public class UserControllerAdvice {
    
    @ExceptionHandler
    public ResponseEntity<ErrorResult> noUserWithNameAndeEmailExHandle(NoUserWithNameAndeEmailException e) {
        ErrorResult errorResult = new ErrorResult(e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler
    public ResponseEntity<ErrorResult> notValidExHandle(MethodArgumentNotValidException e) {
        ErrorResult errorResult = new ErrorResult("올바른 값을 입력해주세요!");
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandle(Exception e) {
        return new ErrorResult("예상치 못한 서버 오류");
    }
}
