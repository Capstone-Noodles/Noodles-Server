package capston.noodles.users.model.dto;

import lombok.Data;

@Data
public class LoginSuccess {

    private int userIdx;

    public LoginSuccess(int userIdx) {
        this.userIdx = userIdx;
    }
}
