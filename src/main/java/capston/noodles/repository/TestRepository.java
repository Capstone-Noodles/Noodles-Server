package capston.noodles.repository;

import capston.noodles.mapper.TestMapper;
import capston.noodles.model.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TestRepository {

    final private TestMapper testMapper;

    public List<Test> findAll() {
        return testMapper.findAll();
    }
}
