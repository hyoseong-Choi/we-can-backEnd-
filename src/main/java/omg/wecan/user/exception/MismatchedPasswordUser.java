package omg.wecan.user.exception;

public class MismatchedPasswordUser extends RuntimeException{
    public MismatchedPasswordUser() {
        super("패스워드가 틀렸습니다.");
    }
}
