package capston.noodles.User.controller;


import capston.noodles.User.model.entity.dto.UpdateProfileDto;
import capston.noodles.User.model.request.MainPostRequest;
import capston.noodles.User.model.response.MypageListResponse;
import capston.noodles.User.model.response.MypageResponse;
import capston.noodles.User.service.MypageService;
import capston.noodles.common.response.ResponseMessage;
import capston.noodles.common.response.ResponseSuccessMessage;
import capston.noodles.users.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
@RequiredArgsConstructor

public class MypageController {
    private final MypageService mypageService;
    private final JwtProvider jwtProvider;

    //마이페이지 정보 조회(친구꺼)
    @GetMapping("/mypage/{userId}")
    public ResponseMessage<MypageListResponse> getUserInfo(@PathVariable("userId") long userId, HttpServletRequest request) {

        long myIdx = jwtProvider.getUserPk(request);
        List<MypageResponse> mypageList = mypageService.getUserInfo(userId, myIdx);

        return new ResponseMessage<>(MypageListResponse.from(mypageList));
    }

    @GetMapping("/mypage")
    public ResponseMessage<MypageListResponse> getMyInfo(HttpServletRequest request) {
        String token = request.getHeader("x-auth-token");
        String userIdStr = jwtProvider.getUserPk(token);
        Long userId = Long.parseLong(userIdStr);

        List<MypageResponse> mypageList = mypageService.getUserInfo(userId);

        return new ResponseMessage<>(MypageListResponse.from(mypageList));
    }

    // 프로필 정보 변경
    @PatchMapping("/mypage/profile")
    public ResponseMessage updateMyProfile(HttpServletRequest request, @ModelAttribute UpdateProfileDto dto) {
        Long userIdx = jwtProvider.getUserPk(request);
        mypageService.updateProfile(userIdx, dto);

        return new ResponseMessage(new ResponseSuccessMessage(200, "프로필 변경 성공!"));
    }

    @PatchMapping("/mypage/mainPost")
    public ResponseMessage updateMainPost(HttpServletRequest request, @RequestBody MainPostRequest mainPostRequest) {
        Long userIdx = jwtProvider.getUserPk(request);
        mainPostRequest.setUserIdx(userIdx);
        mypageService.updateMainPost(mainPostRequest);

        return new ResponseMessage(new ResponseSuccessMessage(200, "대표게시물 설정 성공!"));
    }
}




