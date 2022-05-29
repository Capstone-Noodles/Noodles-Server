package capston.noodles.User.service;

import capston.noodles.User.exception.UpdateProfileException;
import capston.noodles.User.model.entity.dto.UpdateProfileDto;
import capston.noodles.User.model.response.MypageResponse;
import capston.noodles.User.repository.MypageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MypageService {
    private final MypageRepository mypageRepository;

    // 마이페이지 정보 조회
    public List<MypageResponse> getUserInfo(long userId) {
        return mypageRepository.getUserInfo(userId);
    }

    // 프로필 정보 변경
    // UpdateProfileException 발생시 롤백
    @Transactional(rollbackFor = UpdateProfileException.class)
    public void updateProfile(Long userIdx, UpdateProfileDto dto) {
        dto.setUserIdx(userIdx);
        int returnValue = mypageRepository.updateProfile(dto);
        if (returnValue > 1) {
            throw new UpdateProfileException("변경될 열의 갯수가 2개 이상입니다.");
        }
        if (returnValue < 1) {
            throw new UpdateProfileException("변경될 열의 갯수가 0개 입니다. 잘못된 userIdx를 가진 토큰입니다.");
        }
        return;
    }
}
