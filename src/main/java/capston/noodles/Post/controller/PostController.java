package capston.noodles.Post.controller;

import capston.noodles.Post.model.response.AllPostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import capston.noodles.Post.service.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public List<AllPostResponse> getAllPostInfo(@RequestParam("longitude") double longitude, @RequestParam("latitude") double latitude) {
        List<AllPostResponse> AllPostList = postService.getAllPostInfo(longitude, latitude);

        return AllPostList;
    }
}
