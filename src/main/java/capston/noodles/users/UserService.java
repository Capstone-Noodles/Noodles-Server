package capston.noodles.users;

import capston.noodles.common.ErrorCode;
import capston.noodles.users.exception.LoginIdNotFoundException;
import capston.noodles.users.exception.LoginPwdNotCorrectException;
import capston.noodles.users.model.dao.User;
import capston.noodles.users.model.dto.SetRefreshTokenDto;
import capston.noodles.users.model.dto.TokenRequestDto;
import capston.noodles.users.security.JwtProvider;
import capston.noodles.users.security.exception.CRefreshTokenException;
import capston.noodles.users.security.model.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
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


    @Transactional
    public TokenDto reissue(TokenRequestDto dto) {

        // 요청받은 refreshToken 유효성 검사
        if (!jwtProvider.validationToken(dto.getRefreshToken())) {
            throw new CRefreshTokenException("리프레시 토큰이 유효하지 않습니다.");
        }

        // 요청받은 accessToken에서 userIdx 추출
        String accessToken = dto.getAccessToken();
        String userIdx = jwtProvider.getUserPk(accessToken);
        Authentication authentication = jwtProvider.getAuthentication(accessToken);


        // userIdx로 user객체 가져오기
        User user = findById(Integer.parseInt(userIdx));
        String refreshToken = user.getToken();

        // DB에 저장된 refreshToken과 요청받은 refreshToken이 일치하는지 확인
        // 만약 다르다면 토큰이 조작된 것
        if (!refreshToken.equals(dto.getRefreshToken())) {
            throw new CRefreshTokenException("Refresh Token이 조작되었습니다.");
        }

        // 다 통과하면 토큰 생성
        List<String> list = new ArrayList<>();
        list.add(user.getAuthority());
        TokenDto newToken = jwtProvider.createToken(userIdx, list);

        // DB에 새로 발급받은 refreshToken저장
        userRepository.setRefreshToken(SetRefreshTokenDto.builder()
                .identification(userIdx)
                .refreshToken(refreshToken)
                .build()
        );

        return newToken;
    }
}
