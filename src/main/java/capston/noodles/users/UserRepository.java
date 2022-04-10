package capston.noodles.users;

import capston.noodles.users.mapper.UserMapper;
import capston.noodles.users.model.dao.User;
import capston.noodles.users.model.dto.SetRefreshTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final UserMapper userMapper;

    public int insertUser(User user) {
        userMapper.save(user);
        return user.getUserIdx();

    }

    public User findByIdentification(String identification) {
        return userMapper.findByIdentification(identification);
    }



    public User findById(int userPk){
        return userMapper.findById(userPk);
    }

    public void setRefreshToken(SetRefreshTokenDto dto) {

        userMapper.setRefreshToken(dto);
        return;
    }

    public String getRefreshTokenByUserIdx(int userIdx){
        return userMapper.getRefreshTokenByUserIdx(userIdx);
    }


}
