package capston.noodles.Follow.Repository;

import capston.noodles.Follow.mapper.FollowMapper;
import capston.noodles.Follow.model.dao.Follow;
import capston.noodles.Follow.model.dto.GetFollowRequestDto;
import capston.noodles.Follow.model.dto.GetFollowResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FollowRepository {

    private final FollowMapper followMapper;

    public Long insertFollow(Follow follow) {
        followMapper.save(follow);
        return follow.getFollowIdx();

    }

    public Follow getFollowByUserIdxs(Follow follow){
        return followMapper.getFollowByUserIdxs(follow);
    }

    public int updateFollowStatus(Follow follow) {
        return followMapper.updateFollowStatus(follow);

    }

    public List<GetFollowResponse> selectAllFollowerWithUserIdx(GetFollowRequestDto dto){
        return followMapper.selectAllFollowerWithUserIdx(dto);
    }

    public List<GetFollowResponse> selectAllFollowingWithUserIdx(GetFollowRequestDto dto) {
        return followMapper.selectAllFollowingWithUserIdx(dto);
    }
}
