package capston.noodles.users.controller;

import capston.noodles.common.response.ResponseMessage;
import capston.noodles.users.UserService;
import capston.noodles.users.exception.EmailLoginFailedException;
import capston.noodles.users.exception.LoginIdNotFoundException;
import capston.noodles.users.exception.LoginPwdNotCorrectException;
import capston.noodles.users.model.dao.User;
import capston.noodles.users.model.dto.*;
import capston.noodles.users.security.JwtProvider;
import capston.noodles.users.security.model.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/login")
    public ResponseMessage login(@RequestBody LoginRequestDto dto) {


        TokenDto result = userService.login(dto.getId(), dto.getPassword());


        return new ResponseMessage<TokenDto>(result);
    }

    @PostMapping("/users")
    public ResponseMessage signup(@RequestBody SignupDto dto) {
        User user = dto.toUser();
        int userIdx = userService.save(user);
        if (userIdx < 0){
            return new ResponseMessage("회원 아이디가 중복되었습니다.");
        }

        return new ResponseMessage(new LoginSuccess(userIdx));
    }

    @PostMapping("/reissue")
    public ResponseMessage<TokenDto> reissue(@RequestBody TokenRequestDto dto) {
        return new ResponseMessage<TokenDto>(userService.reissue(dto));
    }

//    @GetMapping("v1/test")
//    public String test(HttpServletRequest request){
//        userService.test(request);
//        return "pass";
//    }


}
