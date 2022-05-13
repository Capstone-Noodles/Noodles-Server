package capston.noodles.Comment.controller;

import capston.noodles.Comment.model.CommentRequest;
import capston.noodles.Comment.service.CommentService;
import capston.noodles.users.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

}
