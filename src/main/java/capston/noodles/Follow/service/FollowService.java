package capston.noodles.Follow.service;

import capston.noodles.Follow.Repository.FollowRepository;
import capston.noodles.Follow.exception.ChangeFollowStatusException;
import capston.noodles.Follow.model.dao.Follow;
import capston.noodles.Follow.model.dto.GetFollowResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    @Transactional
    public void changeFollowStatus(Long toUserIdx, Long fromUserIdx) {
        Follow follow = Follow.toFollow(toUserIdx, fromUserIdx);

        // 기존에 존재하는 follow인지 가져오기
        Follow existFollow = isFollowExist(follow);

        // 존재한다면 상태만 바꿔서 재저장
        if(existFollow != null){
            existFollow.changeStatus();
            int returnValue = followRepository.updateFollowStatus(existFollow);
            if (returnValue != 1) {
                throw new ChangeFollowStatusException("Follow/changeFollowStatus Error, 승용이와 함께 고민해봅시다...");
            }
        }else {
            followRepository.insertFollow(follow);
        }
        return;
    }


    /**
     * @return 이미 팔로우 데이터가 DB에 존재하는 경우 true
     * @return 아닌 경우 false
     */
    @Transactional
    public Follow isFollowExist(Follow follow) {
        Follow findFollow = followRepository.getFollowByUserIdxs(follow);

        return findFollow;

    }

    @Transactional
    public List<GetFollowResponse> getFollow(Long userIdx){
        return followRepository.selectAllFollowWithUserIdx(userIdx);
    }
}
