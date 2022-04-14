package capston.noodles.Post.controller;

import capston.noodles.Post.model.response.AllPostResponse;
import capston.noodles.Post.model.response.OnePostResponse;
import capston.noodles.Post.service.PostService;
import capston.noodles.common.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/posts/{postIdx}")
    public List<OnePostResponse> getOnePostInfo(@PathVariable("postIdx") long postIdx) {
        List<OnePostResponse> onePostList = postService.getOnePostInfo(postIdx);

        return onePostList;
    }

    @PostMapping("/api/v1/upload")
    public String uploadImage(@RequestPart MultipartFile file) {
        return postService.uploadImage(file);
    }
}
