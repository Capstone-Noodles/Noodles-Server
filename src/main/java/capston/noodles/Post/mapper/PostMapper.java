package capston.noodles.Post.mapper;

import capston.noodles.Post.model.response.AllPostResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    List<AllPostResponse> getAllPostInfo(@Param("longitude") double longitude, @Param("latitude") double latitude);
}
