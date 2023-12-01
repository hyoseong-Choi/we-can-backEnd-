package omg.wecan.exception;

import java.util.NoSuchElementException;

public class NoUserWithEmailException extends NoSuchElementException {
    public NoUserWithEmailException() {
        super();
    }
    
    public NoUserWithEmailException(String s, Throwable cause) {
        super(s, cause);
    }
    
    public NoUserWithEmailException(Throwable cause) {
        super(cause);
    }
    
    public NoUserWithEmailException(String s) {
        super(s);
    }
}
