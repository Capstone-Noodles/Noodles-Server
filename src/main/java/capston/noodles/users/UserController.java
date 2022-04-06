package capston.noodles.users;

import capston.noodles.users.model.dao.User;
import capston.noodles.users.model.dto.SignupDto;
import capston.noodles.users.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/users")
    public void signUp(@RequestBody SignupDto dto){
        User user = dto.toUser();

        return;
    }

    @GetMapping("/v1/login")
    public String login(@RequestParam String id, @RequestParam String password) {
        return "test";
    }


}
