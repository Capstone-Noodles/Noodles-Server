package capston.noodles.Follow.mapper;

import capston.noodles.Follow.model.dao.Follow;
import capston.noodles.Follow.model.dto.GetFollowResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowMapper {

    void save(Follow follow);

    Follow getFollowByUserIdxs(Follow follow);

    int updateFollowStatus(Follow follow);

    List<GetFollowResponse> selectAllFollowWithUserIdx(Long userIdx);

}
