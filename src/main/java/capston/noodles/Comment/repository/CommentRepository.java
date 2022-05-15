package capston.noodles.Comment.repository;

import capston.noodles.Comment.mapper.CommentMapper;
import capston.noodles.Comment.model.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
