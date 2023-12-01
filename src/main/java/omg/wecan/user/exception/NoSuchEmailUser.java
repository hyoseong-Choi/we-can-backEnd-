package omg.wecan.user.exception;

public class NoSuchEmailUser extends RuntimeException{
    public NoSuchEmailUser() {
        super("등록된 이메일이 없습니다.");
    }
}
