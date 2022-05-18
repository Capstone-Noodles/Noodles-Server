package capston.noodles.Comment.controller;

import capston.noodles.Comment.model.CommentListResponse;
import capston.noodles.Comment.model.CommentRequest;
import capston.noodles.Comment.service.CommentService;
import capston.noodles.common.response.ResponseMessage;
import capston.noodles.users.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final JwtProvider jwtProvider;

    // 댓글 작성 API
    @PostMapping("/comments")
    public int postComment(@RequestBody CommentRequest commentRequest, HttpServletRequest request) {
        String token = request.getHeader("x-auth-token");
        String userIdx = jwtProvider.getUserPk(token);
        commentRequest.setUserIdx(Integer.parseInt(userIdx));
        int result = commentService.postComment(commentRequest);
        return result;
    }

    // 댓글 조회 API
    @GetMapping("/comments/{postIdx}/postComment")
    public List<CommentListResponse> getComments(@PathVariable("postIdx") long postIdx) {
        List<CommentListResponse> commentList = commentService.getComments(postIdx);
        return commentList;
    }
    // 대댓글 조회 API
    @GetMapping("/comments/{commentIdx}/commentComment")
    public List<CommentListResponse> getChildComments(@PathVariable("commentIdx") int commentIdx) {
        List<CommentListResponse> commentList = commentService.getChildComments(commentIdx);
        return commentList;
    }
}
