package capston.noodles.Comment.repository;

import capston.noodles.Comment.mapper.CommentMapper;
import capston.noodles.Comment.model.CommentListResponse;
import capston.noodles.Comment.model.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    private final CommentMapper commentMapper;

    public int postComment(CommentRequest commentRequest) {
        int result = commentMapper.postComment(commentRequest);
        if(result == 1)     // 정상적으로 데이터가 들어갔을 경우
            return 1;
        else
            return 0;
    }

    public List<CommentListResponse> getComments(long postIdx) {
        return commentMapper.getComments(postIdx);
    }

    public List<CommentListResponse> getChildComments(int commentIdx) {
        return commentMapper.getChildComments(commentIdx);
    }

    public Integer checkParentComment(Integer parentCommentIdx) {
        Integer result = commentMapper.checkParentComment(parentCommentIdx);
        return result;
    }

    public int deleteComment(long commentIdx) {
        return commentMapper.deleteComment(commentIdx);
    }

    public int updateComment(long commentIdx, String content) {
        return commentMapper.updateComment(commentIdx, content);
    }
}
