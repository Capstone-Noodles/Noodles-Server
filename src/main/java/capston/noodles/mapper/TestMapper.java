package capston.noodles.mapper;

import capston.noodles.model.Test;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface TestMapper {
    List<Test> findAll();
}
