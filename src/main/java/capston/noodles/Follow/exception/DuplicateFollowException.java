package capston.noodles.Follow.exception;

public class DuplicateFollowException extends RuntimeException {
    public DuplicateFollowException(){
        super();
    }

    public DuplicateFollowException(String message){
        super(message);
    }

    public DuplicateFollowException(String message, Throwable cause) {
        super(message, cause);
    }

}


