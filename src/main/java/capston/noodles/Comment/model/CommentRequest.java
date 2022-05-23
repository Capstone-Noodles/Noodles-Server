package capston.noodles.Comment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentRequest {

    private String content;
    private int postIdx;
    private int userIdx;
    private Integer parentCommentIdx;
}
