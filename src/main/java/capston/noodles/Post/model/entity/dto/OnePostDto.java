package capston.noodles.Post.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OnePostDto {
    private double longitude;
    private double latitude;
    private long userIdx;
    private long postIdx;
}
