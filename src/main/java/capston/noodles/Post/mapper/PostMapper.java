package capston.noodles.Post.mapper;

import capston.noodles.Post.model.entity.dto.TotalUploadPostDto;
import capston.noodles.Post.model.response.AllPostResponse;
import capston.noodles.Post.model.response.OnePostResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    List<AllPostResponse> getAllPostInfo(@Param("longitude") double longitude, @Param("latitude") double latitude);
    List<OnePostResponse> getOnePostInfo(@Param("postIdx") long postIdx);
    Long postPost(TotalUploadPostDto totalUploadPostDto);
    Long insertImage(@Param("postImageIdx") long postImageIdx, @Param("image") String image);
}
