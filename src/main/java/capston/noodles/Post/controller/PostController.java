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

    @PostMapping("/upload")
    public String uploadImage(MultipartFile file) throws IOException {
        String imgPath = postService.uploadImage(file);

        return imgPath;
    }

    @PostMapping("/posts/write")
    public ResponseMessage uploadPost(@RequestBody UploadPostDto uploadPostDto, MultipartFile file) throws IOException {

        String imgPath = postService.uploadImage(file);
        TotalUploadPostDto totalUploadPostDto = TotalUploadPostDto.toTotalUploadPostDto(uploadPostDto, imgPath);
        long postResult = postService.postPost(totalUploadPostDto);

        return new ResponseMessage("hi");
    }

}
