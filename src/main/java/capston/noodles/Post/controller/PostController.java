package capston.noodles.Post.controller;

import capston.noodles.Post.model.entity.dto.LocationDto;
import capston.noodles.Post.model.entity.dto.SearchPostByHashtagDto;
import capston.noodles.Post.model.entity.dto.TotalUploadPostDto;
import capston.noodles.Post.model.entity.dto.UploadPostDto;
import capston.noodles.Post.model.response.AllPostResponse;
import capston.noodles.Post.model.response.OnePostResponse;
import capston.noodles.Post.service.PostService;
import capston.noodles.common.response.ResponseMessage;
import capston.noodles.users.security.JwtProvider;
import com.amazonaws.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final JwtProvider jwtProvider;

    // 모든 게시물 조회(거리 제한)
    @GetMapping("/posts")
    public List<AllPostResponse> getAllPostInfo(@RequestParam("longitude") double longitude, @RequestParam("latitude") double latitude, HttpServletRequest request, @RequestParam(value = "distance", required=false) Integer distance) {
        String token = request.getHeader("x-auth-token");
        String userIdxStr = jwtProvider.getUserPk(token);
        int userIdx = Integer.parseInt(userIdxStr);

        if(distance == null) {
            distance = 20000;
        }
        else {
            distance = distance * 1000;
        }

        List<AllPostResponse> AllPostList = postService.getAllPostInfo(longitude, latitude, userIdx, distance);

        return AllPostList;
    }

    // 게시물 하나 조회
    @GetMapping("/posts/{postIdx}")
    public AllPostResponse getOnePostInfo(@PathVariable("postIdx") long postIdx, @RequestParam("longitude") double longitude, @RequestParam("latitude") double latitude, HttpServletRequest request) {
        Long userIdx = jwtProvider.getUserPk(request);
        List<AllPostResponse> onePostList = postService.getOnePostInfo(longitude, latitude, userIdx, postIdx);

        return onePostList.get(0);
    }

    // 게시물 업로드
    @PostMapping(path = "/posts/write", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseMessage uploadPost(@ModelAttribute UploadPostDto uploadDto, HttpServletRequest request) throws IOException {
        System.out.println(uploadDto);
        List<MultipartFile> imageFileList = uploadDto.getImageFileList();
        Long userIdx = jwtProvider.getUserPk(request);
        postService.postPost(uploadDto, imageFileList, userIdx);
        return new ResponseMessage("hi");
    }

    @PatchMapping("/posts/delete/{postIdx}")
    public  ResponseMessage deletePost(@PathVariable("postIdx") long postIdx) {
        postService.deletePost(postIdx);
        return new ResponseMessage("delete!!");
    }

    @GetMapping("/posts/following")
    public ResponseMessage getMyFollowerPosts(HttpServletRequest request, @RequestParam("longitude") Double longitude, @RequestParam("latitude") Double latitude) {
        Long userPk = jwtProvider.getUserPk(request);

        List<AllPostResponse> myFollowerPosts = postService.getMyFollowerPosts(longitude, latitude, userPk);
        return new ResponseMessage(myFollowerPosts);
    }

    // 내 게시물 조회 API
    @GetMapping("/myPosts")
    public ResponseMessage getMyPosts(HttpServletRequest request) {
        Long userIdx = jwtProvider.getUserPk(request);

        List<AllPostResponse> result = postService.getMyPosts(userIdx);
        return new ResponseMessage(result);
    }

    // 게시물 좋아요 API
    @PostMapping("/posts/like/{postIdx}")
    public  ResponseMessage likePost(@PathVariable("postIdx") long postIdx, HttpServletRequest request) {
        Long userIdx = jwtProvider.getUserPk(request);

        postService.likePost(userIdx, postIdx);
        return new ResponseMessage("success like");
    }

    /**
     * 검색 API / 최승용 / 2022-06-03
     * Body로 longitude,latitude,hashtag 를 입력받
     */
    @GetMapping("/posts/search")
    public ResponseMessage searchPosts(@ModelAttribute SearchPostByHashtagDto dto, HttpServletRequest request) {
        Long userIdx = jwtProvider.getUserPk(request);
        List<AllPostResponse> postsByHashtag = postService.getPostsByHashtag(dto, userIdx);
        return new ResponseMessage(postsByHashtag);
    }



}
