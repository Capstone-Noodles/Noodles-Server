package capston.noodles.users.model.dto;

import capston.noodles.users.model.dao.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class SignupDto {
    String id;
    String password;
    String phoneNum;
    String nickName;

    public User toUser(){
        User user = new User();
        user.setIdentification(id);
        user.setPassword(password);
        user.setPhoneNumber(phoneNum);
        user.setNickname(nickName);

        return user;
    }
}
