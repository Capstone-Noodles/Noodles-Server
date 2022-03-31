package capston.noodles.users;

import capston.noodles.users.model.dao.User;
import capston.noodles.users.model.dto.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public void signUp(@RequestBody SignupDto dto){
        User user = dto.toUser();

        return;
    }
}
