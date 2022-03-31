package capston.noodles.User.repository;

import capston.noodles.User.mapper.MypageMapper;
import capston.noodles.User.model.response.MypageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MypageRepository {
    private final MypageMapper mypageMapper;

    public List<MypageResponse> getUserInfo(long userId) {
        return mypageMapper.getUserInfo(userId);
    }
}
