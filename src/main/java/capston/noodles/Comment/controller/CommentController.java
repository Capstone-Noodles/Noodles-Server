package capston.noodles.Comment.controller;

import capston.noodles.Comment.model.CommentListResponse;
import capston.noodles.Comment.model.CommentRequest;
import capston.noodles.Comment.service.CommentService;
import capston.noodles.common.response.ResponseMessage;
import capston.noodles.common.response.ResponseSuccessMessage;
import capston.noodles.users.security.JwtProvider;
import com.amazonaws.Response;
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
    public ResponseMessage postComment(@RequestBody CommentRequest commentRequest, HttpServletRequest request) {
        long userIdx = jwtProvider.getUserPk(request);
        commentRequest.setUserIdx(userIdx);

        int result = commentService.postComment(commentRequest);
        if(result == 1) {
            return new ResponseMessage(new ResponseSuccessMessage(200, "댓글달기 성공"));
        }
        else {
            return new ResponseMessage("댓글달기 실패");
        }
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

    // 댓글 삭제 API
    @DeleteMapping("/comments/{commentIdx}")
    public ResponseMessage deleteComment(@PathVariable("commentIdx") long commentIdx) {
        int result = commentService.deleteComment(commentIdx);
        if(result == 1) {
            return new ResponseMessage(new ResponseSuccessMessage(200, "댓글 삭제 완료"));
        }
        else {
            return new ResponseMessage("댓글 삭제 실패");
        }
    }

    @PatchMapping("/comments/{commentIdx}")
    public ResponseMessage updateComment(@PathVariable("commentIdx") long commentIdx, @RequestBody CommentRequest commentRequest, HttpServletRequest request) {
        long userIdx = jwtProvider.getUserPk(request);
        commentRequest.setUserIdx(userIdx);
        int result = commentService.updateComment(commentIdx, commentRequest.getContent());
        if(result == 1) {
            return new ResponseMessage(new ResponseSuccessMessage(200, "댓글 수정 완료"));
        }
        else {
            return new ResponseMessage("댓글 수정 실패");
        }
    }
}
