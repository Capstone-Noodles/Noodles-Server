package capston.noodles.users.model.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDao {
    String id;
    String password;
    String phoneNum;
    String nickName;
}
