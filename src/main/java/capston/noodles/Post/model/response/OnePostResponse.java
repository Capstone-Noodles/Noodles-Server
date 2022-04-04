package capston.noodles.Post.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OnePostResponse {
    private String createdAt;
    private String location;
    private double latitude;
    private double longitude;
    private String content;
    private String nickname;
    private String profileImg;
}
