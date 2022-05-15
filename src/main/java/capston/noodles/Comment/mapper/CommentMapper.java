package capston.noodles.Comment.mapper;

import capston.noodles.Comment.model.CommentRequest;

public interface CommentMapper {
    int postComment(CommentRequest commentRequest);
}
