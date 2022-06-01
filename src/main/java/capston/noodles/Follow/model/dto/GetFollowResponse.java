package capston.noodles.Follow.model.dto;

import lombok.Data;

@Data
public class GetFollowResponse {
    private Long userIdx;
    private String nickname;
    private String profileImage;
    private String identification;
    private Boolean isFollowing;
}
