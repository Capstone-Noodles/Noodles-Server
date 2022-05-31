package capston.noodles.Post.repository;

import capston.noodles.Post.mapper.PostMapper;
import capston.noodles.Post.model.entity.Post;
import capston.noodles.Post.model.entity.PostImage;
import capston.noodles.Post.model.entity.dto.LocationDto;
import capston.noodles.Post.model.entity.dto.OnePostDto;
import capston.noodles.Post.model.entity.dto.TotalUploadPostDto;
import capston.noodles.Post.model.response.AllPostResponse;
import capston.noodles.Post.model.response.OnePostResponse;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.One;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final PostMapper postMapper;

    public List<AllPostResponse> getAllPostInfo(double longitude, double latitude, int userIdx) {
        return postMapper.getAllPostInfo(longitude, latitude, userIdx);
    }

    public List<AllPostResponse> getOnePostInfo(OnePostDto dto) {
        return postMapper.getOnePostInfo(dto);
    }

    public void postPost(Post post) {
        postMapper.postPost(post);
        return;
    }

    public void postImage(PostImage postImage) {
        postMapper.insertImage(postImage);
        return;
    }

    public void deletePost(long postIdx) {
        postMapper.deletePost(postIdx);
    }

    public List<AllPostResponse> getMyFollowerPosts(LocationDto dto) {
        return postMapper.getMyFollowerPosts(dto);
    }

    public List<AllPostResponse> getMyPosts(Long userIdx) {
        return postMapper.getMyPosts(userIdx);
    }

    public void likePost(long userIdx, long postIdx) { postMapper.likePost(userIdx, postIdx); }
}
