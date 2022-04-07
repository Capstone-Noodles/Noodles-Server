package capston.noodles.users.mapper;

import capston.noodles.users.model.dao.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void save(User user);
    User findById(int userPk);
    User findByIdentification(String identification);
}