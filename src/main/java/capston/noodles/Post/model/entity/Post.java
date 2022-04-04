package capston.noodles.Post.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private int postIdx;
    private int userIdx;
    private Date createdAt;
    private Date updatedAt;
    private String status;
    private String location;
    private double latitude;
    private double longitude;
    private String content;
    private String isMain;
}
