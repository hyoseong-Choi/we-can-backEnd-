package omg.wecan.exception.customException;

import omg.wecan.auth.exception.EmptyAuthorizationHeaderException;
import omg.wecan.jwt.exception.InvalidTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ErrorResponse> handleMayoException( CustomException e){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(e.getCode().getCode())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(e.getCode().getStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidException( MethodArgumentNotValidException e){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("400")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(e.getStatusCode())
                .body(errorResponse);
    }

    @ExceptionHandler(value = EmptyAuthorizationHeaderException.class)
    public ResponseEntity<ErrorResponse> handleTokenException( EmptyAuthorizationHeaderException e){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("400")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
    @ExceptionHandler(value = InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> handleTokenException( InvalidTokenException e){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("400")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}
