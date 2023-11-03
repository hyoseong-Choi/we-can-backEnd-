package omg.wecan.exception;

import java.util.NoSuchElementException;

public class NoUserWithNameAndeEmailException extends NoSuchElementException {
    public NoUserWithNameAndeEmailException() {
        super();
    }
    
    public NoUserWithNameAndeEmailException(String s, Throwable cause) {
        super(s, cause);
    }
    
    public NoUserWithNameAndeEmailException(Throwable cause) {
        super(cause);
    }
    
    public NoUserWithNameAndeEmailException(String s) {
        super(s);
    }
}
