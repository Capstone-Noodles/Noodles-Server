package capston.noodles.users;

import capston.noodles.users.model.dao.User;
import capston.noodles.users.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;

@Service
//@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, @Lazy JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthority("ROLE_USER");
        if (userRepository.findByIdentification(user.getIdentification()) == null) {

            userRepository.insertUser(user);
            return;
        }
        System.out.println("아이디 중복");
        return;
    }

    @Transactional
    public String login(String id, String password){
        User user = userRepository.findByIdentification(id);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "Wrong password";
        }
        ArrayList<String> list = new ArrayList<>();
        list.add(user.getAuthority());
        return jwtProvider.createToken(String.valueOf(user.getUserIdx()), list);
    }


    @Transactional
    public User findById(int userPk){
        return userRepository.findById(userPk);
    }
}
