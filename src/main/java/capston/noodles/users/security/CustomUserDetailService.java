package capston.noodles.users.security;

import capston.noodles.users.UserService;
import capston.noodles.users.model.dao.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userPk) throws UsernameNotFoundException {
        User user = userService.findById(Integer.parseInt(userPk));
        if(user != null){
            return new SecurityUser(user);
        }
        return null;
    }
}
