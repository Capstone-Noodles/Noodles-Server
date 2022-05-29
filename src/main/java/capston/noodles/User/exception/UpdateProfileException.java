package capston.noodles.User.exception;

public class UpdateProfileException extends RuntimeException {
    public UpdateProfileException() {
    }

    public UpdateProfileException(String message) {
        super(message);
    }

    public UpdateProfileException(String message, Throwable cause) {
        super(message, cause);
    }
}
