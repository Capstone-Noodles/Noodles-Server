package capston.noodles.users.model.dto;

import lombok.Data;

@Data
public class UserLoginRequestDto {
    private String id;
    private String password;
}
