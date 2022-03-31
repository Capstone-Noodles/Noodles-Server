package capston.noodles.common.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage<T> {
    private final String errorMessage;
    private final T result;

    public ResponseMessage(T result) {
        this.errorMessage = null;
        this.result = result;
    }

    public ResponseMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        this.result = null;
    }

    public ResponseMessage(String errorMessage, T result) {
        this.errorMessage = errorMessage;
        this.result = result;
    }

    public ResponseMessage(HttpStatus httpStatus) {
        this.errorMessage = httpStatus.getReasonPhrase();
        this.result = null;
    }

    public ResponseMessage(HttpStatus httpStatus, T result) {
        this.errorMessage = httpStatus.getReasonPhrase();
        this.result = result;
    }

}