package capston.noodles.users.exception;

public class LoginPwdNotCorrectException extends RuntimeException {
    public LoginPwdNotCorrectException() {
        super();
    }

    public LoginPwdNotCorrectException(String message) {
        super(message);
    }

    public LoginPwdNotCorrectException(String message, Throwable cause) {
        super(message, cause);
    }
}
