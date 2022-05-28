package capston.noodles.Follow.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetFollowRequestDto {
    private Long myIdx;
    private Long targetIdx;
}
