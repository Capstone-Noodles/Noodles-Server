package capston.noodles.User.controller;


import capston.noodles.User.model.response.MypageListResponse;
import capston.noodles.User.model.response.MypageResponse;
import capston.noodles.User.service.MypageService;
import capston.noodles.common.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class MypageController {
    private final MypageService mypageService;

    //마이페이지 정보 조회
    @GetMapping("/mypage/{userId}")
    public ResponseMessage<MypageListResponse> getUserInfo(@PathVariable("userId") long userId){
        List<MypageResponse> mypageList = mypageService.getUserInfo(userId);

        return new ResponseMessage<>(MypageListResponse.from(mypageList));
    }


}

