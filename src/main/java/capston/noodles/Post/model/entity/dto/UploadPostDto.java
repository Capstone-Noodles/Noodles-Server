package capston.noodles.Post.model.entity.dto;

import capston.noodles.Post.model.entity.Post;
import capston.noodles.Post.model.entity.PostImage;
import lombok.Data;

@Data
public class UploadPostDto {
    private double longitude;
    private double latitude;
    private String location;
    private String content;

    public Post toPost() {
        Post post = new Post();
        post.setLongitude(longitude);
        post.setLatitude(latitude);
        post.setLocation(location);
        post.setContent(content);
        return post;
    }
}