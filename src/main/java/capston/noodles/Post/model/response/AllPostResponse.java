package capston.noodles.Post.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AllPostResponse {
    private int postIdx;
    private int userIdx;
    private String nickname;
    private String profileImage;
    private String postImageList;
    private String location;
    private String content;
    private String distance;
    private int likes;
    private int isLiked;
    private int isBookmarked;
}
