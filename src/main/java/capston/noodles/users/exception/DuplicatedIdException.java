package capston.noodles.users.exception;

public class DuplicatedIdException extends RuntimeException {
    public DuplicatedIdException(){
        super();
    }

    public DuplicatedIdException(String message){
        super(message);
    }

    public DuplicatedIdException(String message, Throwable cause) {
        super(message, cause);
    }

}

