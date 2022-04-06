package capston.noodles.users;

import capston.noodles.users.model.dao.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void save(User user) {
        userRepository.insertUser(user);
        return;
    }

    @Transactional
    public User findById(int userPk){
        return userRepository.findById(userPk);
    }
}
