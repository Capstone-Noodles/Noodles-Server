package capston.noodles.Comment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentListResponse {
    private int commentIdx;
    private String content;
    private int parentCommentIdx;
    private int userIdx;
    private String profileImage;
    private String date;
}
