package capston.noodles.User.exception;

public class UpdateMainPostException extends RuntimeException {
    public UpdateMainPostException() {
    }

    public UpdateMainPostException(String message) {
        super(message);
    }

    public UpdateMainPostException(String message, Throwable cause) {
        super(message, cause);
    }
}
