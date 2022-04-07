package capston.noodles.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    DuplicatedIdFound(6000, "Duplicate ID");

    private int code;
    private String description;
}
