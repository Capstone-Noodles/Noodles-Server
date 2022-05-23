package capston.noodles.Comment.mapper;

import capston.noodles.Comment.model.CommentListResponse;
import capston.noodles.Comment.model.CommentRequest;

import java.util.List;

public interface CommentMapper {
    int postComment(CommentRequest commentRequest);
    List<CommentListResponse> getComments(long postIdx);
    List<CommentListResponse> getChildComments(int commentIdx);
    Integer checkParentComment(Integer parentCommentIdx);
}
