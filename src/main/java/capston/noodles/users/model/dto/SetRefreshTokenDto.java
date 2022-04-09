package capston.noodles.users.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SetRefreshTokenDto {
    private String identification;
    private String refreshToken;
}
