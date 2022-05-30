package capston.noodles.Comment.service;

import capston.noodles.Comment.exception.ParentCommentNotExist;
import capston.noodles.Comment.model.CommentListResponse;
import capston.noodles.Comment.model.CommentRequest;
import capston.noodles.Comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public int postComment(CommentRequest commentRequest) {
        if(commentRequest.getParentCommentIdx() != null ) {
            Integer checkResult = commentRepository.checkParentComment(commentRequest.getParentCommentIdx());
            if(checkResult == null) {
                throw new ParentCommentNotExist("존재하지 않는 부모댓글입니다.");
            }
        }

        int result = commentRepository.postComment(commentRequest);
        return result;
    }

    public List<CommentListResponse> getComments(long postIdx) {
        return commentRepository.getComments(postIdx);
    }

    public List<CommentListResponse> getChildComments(int commentIdx) {
        return commentRepository.getChildComments(commentIdx);
    }

    public int deleteComment(long commentIdx) {
        return commentRepository.deleteComment(commentIdx);
    }

    public int updateComment(long commentIdx, String content) {
        return commentRepository.updateComment(commentIdx, content);
    }

}
