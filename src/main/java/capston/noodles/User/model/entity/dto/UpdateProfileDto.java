package capston.noodles.User.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateProfileDto {
    private Long userIdx;
    private MultipartFile imageFile;
    private String profileImage;
    private String nickname;
    private String description;
}
