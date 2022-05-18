package capston.noodles.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseSuccessMessage {
    private int code;
    private String message;

}
