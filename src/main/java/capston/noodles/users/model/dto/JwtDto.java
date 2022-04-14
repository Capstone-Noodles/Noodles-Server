package capston.noodles.users.model.dto;

import lombok.Data;

@Data
public class JwtDto {
    private String token;

    public JwtDto(String token) {
        this.token = token;
    }
}
