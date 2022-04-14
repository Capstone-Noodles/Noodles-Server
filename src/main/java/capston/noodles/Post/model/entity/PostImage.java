package capston.noodles.Post.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostImage {
    private int postImageIdx;
    private Date createdAt;
    private Date updatedAt;
    private String status;
    private String image;
    private int postIdx;
}