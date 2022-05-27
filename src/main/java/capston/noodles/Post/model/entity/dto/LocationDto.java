package capston.noodles.Post.model.entity.dto;

import lombok.Data;

@Data
public class LocationDto {
    private Double longitude;
    private Double latitude;
    private Long userIdx;
}
