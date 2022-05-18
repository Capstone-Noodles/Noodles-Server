package capston.noodles.Follow.service;

import capston.noodles.Follow.Repository.FollowRepository;
import capston.noodles.Follow.exception.DuplicateFollowException;
import capston.noodles.Follow.model.dao.Follow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    @Transactional
    public void addFollow(Long toUserIdx, Long fromUserIdx) {
        Follow follow = Follow.toFollow(toUserIdx, fromUserIdx);
        if(isOnFollow(follow)){
            throw new DuplicateFollowException("이미 팔로우 되어 있는 상태입니다.");
        }
        followRepository.insertFollow(follow);

        return;
    }

    public void test(Long toUserIdx, Long fromUserIdx) {
        Follow follow = Follow.toFollow(toUserIdx, fromUserIdx);
        isOnFollow(follow);
    }


    /**
     *
     * @return 이미 팔로우인 경우 true
     * @return 팔로우가 아닌 경우 false
     */
    @Transactional
    public boolean isOnFollow(Follow follow) {
        Long findFollow = followRepository.getFollowByUserIdxs(follow);

        return findFollow == null ? false : true;

    }
}
