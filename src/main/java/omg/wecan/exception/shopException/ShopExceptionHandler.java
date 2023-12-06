package omg.wecan.exception.shopException;

import omg.wecan.exception.customException.ErrorResponse;
import omg.wecan.shop.controller.ShopController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = ShopController.class)
public class ShopExceptionHandler {
    
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> NoUserItemException(NoUserItemException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(e.getCode().getCode())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(e.getCode().getStatus())
                .body(errorResponse);
    }
    
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> LackOfCandyException(LackOfCandyException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(e.getCode().getCode())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(e.getCode().getStatus())
                .body(errorResponse);
    }
}
