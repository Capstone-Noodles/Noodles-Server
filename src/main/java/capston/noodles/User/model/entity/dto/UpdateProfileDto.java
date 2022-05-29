package capston.noodles.User.model.entity.dto;

import lombok.Data;

@Data
public class UpdateProfileDto {
    private Long userIdx;
    private String nickname;
    private String description;
}
