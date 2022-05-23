package capston.noodles.Comment.exception;

public class ParentCommentNotExist extends RuntimeException{
    public ParentCommentNotExist() {
    }

    public ParentCommentNotExist(String message) {
        super(message);
    }

    public ParentCommentNotExist(String message, Throwable cause) {
        super(message, cause);
    }
}
