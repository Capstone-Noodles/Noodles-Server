package capston.noodles.users;

import capston.noodles.common.ErrorCode;
import capston.noodles.users.exception.LoginIdNotFoundException;
import capston.noodles.users.exception.LoginPwdNotCorrectException;
import capston.noodles.users.model.dao.User;
import capston.noodles.users.model.dto.SetRefreshTokenDto;
import capston.noodles.users.security.JwtProvider;
import capston.noodles.users.security.model.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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
    public int save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthority("ROLE_USER");
        if (userRepository.findByIdentification(user.getIdentification()) == null) {

            return userRepository.insertUser(user);
        }
        return -1;
    }

    @Transactional
    public TokenDto login(String id, String password){
        User user = userRepository.findByIdentification(id);
        if (user == null) {
            throw new LoginIdNotFoundException();
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new LoginPwdNotCorrectException();
        }
        ArrayList<String> list = new ArrayList<>();
        list.add(user.getAuthority());

        TokenDto tokenDto = jwtProvider.createToken(String.valueOf(user.getUserIdx()), list);
        String refreshToken = tokenDto.getRefreshToken();

        userRepository.setRefreshToken(SetRefreshTokenDto.builder()
                .identification(id)
                .refreshToken(refreshToken)
                .build()
        );

        return tokenDto;


    }


    @Transactional
    public User findById(int userPk){
        return userRepository.findById(userPk);
    }

//    public void test(HttpServletRequest request){
//        System.out.println(jwtProvider.getUserPk(jwtProvider.resolveToken(request)));
//    }

}
