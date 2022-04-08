package capston.noodles.users.exception;

public class LoginIdNotFoundException extends RuntimeException {
    public LoginIdNotFoundException() {
        super();
    }

    public LoginIdNotFoundException(String message) {
        super(message);
    }

    public LoginIdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
