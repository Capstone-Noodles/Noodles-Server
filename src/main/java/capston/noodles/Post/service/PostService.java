package capston.noodles.Post.service;

import capston.noodles.Post.model.response.AllPostResponse;
import capston.noodles.Post.model.response.OnePostResponse;
import capston.noodles.Post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<AllPostResponse> getAllPostInfo(double longitude, double latitude) {
        return postRepository.getAllPostInfo(longitude, latitude);
    }

    public List<OnePostResponse> getOnePostInfo(long postIdx) {
        return postRepository.getOnePostInfo(postIdx);
    }
}
