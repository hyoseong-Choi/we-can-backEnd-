package omg.wecan.charity.exception;

public class NoSuchCharityException extends RuntimeException{
    public NoSuchCharityException(final String message){
        super(message);
    }
    public NoSuchCharityException(){
        this("존재하지 않는 기부단체입니다.");
    }
}
