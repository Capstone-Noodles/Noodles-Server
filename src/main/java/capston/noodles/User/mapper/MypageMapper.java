package capston.noodles.User.mapper;

import capston.noodles.User.model.response.MypageResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MypageMapper {
    List<MypageResponse> getUserInfo(@Param("userId") long userId);
}
