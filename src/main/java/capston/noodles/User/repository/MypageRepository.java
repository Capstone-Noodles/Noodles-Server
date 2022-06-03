package capston.noodles.User.repository;

import capston.noodles.User.exception.UpdateMainPostException;
import capston.noodles.User.mapper.MypageMapper;
import capston.noodles.User.model.entity.dto.UpdateProfileDto;
import capston.noodles.User.model.request.MainPostRequest;
import capston.noodles.User.model.response.MypageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MypageRepository {
    private final MypageMapper mypageMapper;

    // 친구꺼
    public List<MypageResponse> getUserInfo(long userId, long myIdx) {
        return mypageMapper.getFriendUserInfo(userId, myIdx);
    }
    public List<MypageResponse> getUserInfo(long userId) {
        return mypageMapper.getUserInfo(userId);
    }

    public int updateProfile(UpdateProfileDto dto) {
       return mypageMapper.updateProfile(dto);
    }

    public String getImageByUserIdx(Long userIdx) {
        return mypageMapper.getImageByUserIdx(userIdx);
    }

    public int updateMainPost(MainPostRequest mainPostRequest) {
        Long userIdx = mainPostRequest.getUserIdx();
        List<Long> postIdxList = mainPostRequest.getPostIdxList();

        int removeMainResult = mypageMapper.removeMain(userIdx);

        int length = postIdxList.size();
        for(int i = 0; i<length; i++) {
            int addMainResult = mypageMapper.addMain(postIdxList.get(i), userIdx);
            if(addMainResult != 1) {
                throw new UpdateMainPostException("대표 게시물로 설정되지 않았습니다.");
            }
        }
        return 1;
    }
}
