package capston.noodles.users;

import capston.noodles.common.response.ResponseMessage;
import capston.noodles.users.model.dao.User;
import capston.noodles.users.model.dto.JwtDto;
import capston.noodles.users.model.dto.LoginRequestDto;
import capston.noodles.users.model.dto.LoginSuccess;
import capston.noodles.users.model.dto.SignupDto;
import capston.noodles.users.security.JwtProvider;
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


        String result = userService.login(dto.getId(), dto.getPassword());
        if (result.equals("Wrong Id")) {
            return new ResponseMessage("입력하신 id와 일치하는 계정이 없습니다.");
        }
        if(result.equals("Wrong password"))
            return new ResponseMessage("비밀번호가 잘못되었습니다.");

        return new ResponseMessage(new JwtDto(result));
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

    @GetMapping("v1/test")
    public String test(HttpServletRequest request){
        userService.test(request);
        return "pass";
    }


}
