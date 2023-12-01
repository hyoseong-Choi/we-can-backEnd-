package omg.wecan.exception;

import java.util.NoSuchElementException;

public class NoUserWithNameAndEmailException extends NoSuchElementException {
    public NoUserWithNameAndEmailException() {
        super();
    }
    
    public NoUserWithNameAndEmailException(String s, Throwable cause) {
        super(s, cause);
    }
    
    public NoUserWithNameAndEmailException(Throwable cause) {
        super(cause);
    }
    
    public NoUserWithNameAndEmailException(String s) {
        super(s);
    }
}
