package capston.noodles.Post.controller;

import capston.noodles.Post.model.entity.dto.TotalUploadPostDto;
import capston.noodles.Post.model.entity.dto.UploadPostDto;
import capston.noodles.Post.model.response.AllPostResponse;
import capston.noodles.Post.model.response.OnePostResponse;
import capston.noodles.Post.service.PostService;
import capston.noodles.common.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
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

    @PostMapping("/posts/write")
    public ResponseMessage uploadPost(@RequestPart(value = "uploadDto") UploadPostDto uploadPostDto, @RequestParam("imageFileList") List<MultipartFile> imageFileList) throws IOException {

        postService.postPost(uploadPostDto, imageFileList);
        return new ResponseMessage("hi");
    }

    @PatchMapping("/posts/delete/{postIdx}")
    public  ResponseMessage deletePost(@PathVariable("postIdx") long postIdx) {
        postService.deletePost(postIdx);
        return new ResponseMessage("delete!!");
    }

}
