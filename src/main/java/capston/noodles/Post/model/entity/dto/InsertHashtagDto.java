package capston.noodles.Post.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InsertHashtagDto {
    private Long postIdx;
    private String hashtagWord;

}
