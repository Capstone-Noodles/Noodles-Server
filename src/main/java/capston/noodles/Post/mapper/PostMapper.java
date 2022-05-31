package capston.noodles.Post.mapper;

import capston.noodles.Post.model.entity.Post;
import capston.noodles.Post.model.entity.PostImage;
import capston.noodles.Post.model.entity.dto.LocationDto;
import capston.noodles.Post.model.entity.dto.OnePostDto;
import capston.noodles.Post.model.entity.dto.TotalUploadPostDto;
import capston.noodles.Post.model.response.AllPostResponse;
import capston.noodles.Post.model.response.OnePostResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    List<AllPostResponse> getAllPostInfo(@Param("longitude") double longitude, @Param("latitude") double latitude, @Param("userIdx") int userIdx);
    List<AllPostResponse> getOnePostInfo(OnePostDto dto);
    void postPost(Post post);
    Long insertImage(PostImage postImage);
    void deletePost(long postIdx);
    List<AllPostResponse> getMyFollowerPosts(LocationDto dto);
    List<AllPostResponse> getMyPosts(Long userIdx);
    void likePost(long userIdx, long postIdx);
}
