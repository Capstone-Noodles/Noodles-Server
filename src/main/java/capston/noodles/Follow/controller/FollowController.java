/**
 * 20220-05-18
 * 최승용
 *
 */

package capston.noodles.Follow.controller;

import capston.noodles.Follow.model.dto.GetFollowResponse;
import capston.noodles.Follow.service.FollowService;
import capston.noodles.common.response.ResponseMessage;
import capston.noodles.common.response.ResponseSuccessMessage;
import capston.noodles.users.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final JwtProvider jwtProvider;
    private final FollowService followService;

    /**
     * @param toUserIdx : 팔로잉 대상 유저 idx
     * @param request   : request 헤더의 토큰을 통해 팔로우를 신청하는 유저의 idx를 뽑아냄
     * @return api요청 성공시 ResponseSuccessMessage(코드:200, msg: 팔로우 추가 or 삭제 api 요청 성공)
     * api 실패시 에러보냄
     */
    @PostMapping("/follow/{toUserIdx}")
    public ResponseMessage changeFollowStatus(@PathVariable("toUserIdx") long toUserIdx, HttpServletRequest request) {
        String token = request.getHeader("x-auth-token");
        String fromUserIdx = jwtProvider.getUserPk(token);

        followService.changeFollowStatus(toUserIdx, Long.parseLong(fromUserIdx));

        return new ResponseMessage(new ResponseSuccessMessage(200, "팔로우 추가 or 삭제 api 요청 성공"));
    }

    @GetMapping("/follow/{userIdx}")
    public ResponseMessage<List<GetFollowResponse>> getFollow(@PathVariable("userIdx") Long userIdx){
        return new ResponseMessage<List<GetFollowResponse>>(followService.getFollow(userIdx));
    }
}
