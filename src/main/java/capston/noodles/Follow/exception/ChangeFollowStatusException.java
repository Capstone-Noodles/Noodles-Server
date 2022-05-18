package capston.noodles.Follow.exception;

public class ChangeFollowStatusException extends RuntimeException {
    public ChangeFollowStatusException() {
    }

    public ChangeFollowStatusException(String message) {
        super(message);
    }

    public ChangeFollowStatusException(String message, Throwable cause) {
        super(message, cause);
    }
}
