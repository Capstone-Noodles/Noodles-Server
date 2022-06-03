package capston.noodles.User.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainPostRequest {
    private Long userIdx;
    private List<Long> postIdxList;
}
