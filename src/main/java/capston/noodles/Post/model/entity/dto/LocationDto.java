package capston.noodles.Post.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationDto {
    private Double longitude;
    private Double latitude;
    private Long userIdx;
}
