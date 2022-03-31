package capston.noodles.User.service;

import capston.noodles.User.model.response.MypageResponse;
import capston.noodles.User.repository.MypageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MypageService {
    private final MypageRepository mypageRepository;

    // 마이페이지 정보 조회
    public List<MypageResponse> getUserInfo(long userId) {
        return mypageRepository.getUserInfo(userId);
    }
}
