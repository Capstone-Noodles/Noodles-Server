package capston.noodles.users.model.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String id;
    private String password;
}
