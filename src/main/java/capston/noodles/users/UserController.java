package capston.noodles.users;

import capston.noodles.common.response.ResponseMessage;
import capston.noodles.users.model.dao.User;
import capston.noodles.users.model.dto.JwtDto;
import capston.noodles.users.model.dto.LoginRequestDto;
import capston.noodles.users.model.dto.SignupDto;
import capston.noodles.users.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public void signUp(@RequestBody SignupDto dto){
        User user = dto.toUser();

        return;
    }

    @PostMapping("/v1/login")
    public ResponseMessage login(@RequestBody LoginRequestDto dto) {


        String result = userService.login(dto.getId(), dto.getPassword());


        return new ResponseMessage(new JwtDto(result));
    }

    @PostMapping("/v1/signup")
    public void signup(@RequestBody SignupDto dto) {
        User user = dto.toUser();
        userService.save(user);
        return;
    }

    @GetMapping("v1/test")
    public String test(){
        System.out.println("here");
        return "pass";
    }


}
