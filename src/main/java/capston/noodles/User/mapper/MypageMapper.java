package capston.noodles.User.mapper;

import capston.noodles.User.model.entity.dto.UpdateProfileDto;
import capston.noodles.User.model.response.MypageResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MypageMapper {
    List<MypageResponse> getFriendUserInfo(@Param("userId") long userId, @Param("myIdx") long myIdx);
    List<MypageResponse> getUserInfo(long userId);

    int updateProfile(UpdateProfileDto dto);

    String getImageByUserIdx(Long userIdx);
}
