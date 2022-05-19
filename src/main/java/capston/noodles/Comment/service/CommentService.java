package capston.noodles.Comment.service;

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
        int result = commentRepository.postComment(commentRequest);
        return result;
    }

    public List<CommentListResponse> getComments(long postIdx) {
        return commentRepository.getComments(postIdx);
    }

    public List<CommentListResponse> getChildComments(int commentIdx) {
        return commentRepository.getChildComments(commentIdx);
    }

}
