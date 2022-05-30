package capston.noodles.common.S3;

public class S3Exception extends RuntimeException {
    public S3Exception() {
    }

    public S3Exception(String message) {
        super(message);
    }

    public S3Exception(String message, Throwable cause) {
        super(message, cause);
    }

}
