package capston.noodles.Follow.model.dao;

import lombok.Data;

import java.sql.Date;

@Data
public class Follow {
    private Long followIdx;
    private Date createAt;
    private Date updateAt;
    private char status;
    private Long userIdx;
    private Long followingIdx;

    public static Follow toFollow(Long toUserIdx, Long FromUserIdx) {
        Follow follow = new Follow();
        follow.setUserIdx(FromUserIdx);
        follow.setFollowingIdx(toUserIdx);
        follow.setStatus('Y');

        return follow;
    }
}
