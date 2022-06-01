package capston.noodles.Post.model.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Hashtag {
    private Long hashtagIdx;
    private Date createdAt;
    private Date updatedAt;
    private Character status;
    private String hashtagWord;
    private Long postIdx;
}
