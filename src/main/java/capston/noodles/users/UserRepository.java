package capston.noodles.users;

import capston.noodles.users.mapper.UserMapper;
import capston.noodles.users.model.dao.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final UserMapper userMapper;

    public void insertUser(User user) {
        userMapper.save(user);
    }

    public User findById(int userPk){
        return userMapper.findById(userPk);
    }


}
