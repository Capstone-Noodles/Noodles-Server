package capston.noodles.Post.repository;

import capston.noodles.Post.mapper.PostMapper;
import capston.noodles.Post.model.entity.Post;
import capston.noodles.Post.model.entity.PostImage;
import capston.noodles.Post.model.entity.dto.TotalUploadPostDto;
import capston.noodles.Post.model.response.AllPostResponse;
import capston.noodles.Post.model.response.OnePostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final PostMapper postMapper;

    public List<AllPostResponse> getAllPostInfo(double longitude, double latitude) {
        return postMapper.getAllPostInfo(longitude, latitude);
    }

    public List<OnePostResponse> getOnePostInfo(long postIdx) {
        return postMapper.getOnePostInfo(postIdx);
    }

    public void postPost(Post post) {
        postMapper.postPost(post);
        return;
    }

    public void postImage(PostImage postImage) {


        postMapper.insertImage(postImage);
        return;
    }
}
