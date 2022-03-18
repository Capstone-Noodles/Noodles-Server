package capston.noodles.service;

import capston.noodles.model.Test;
import capston.noodles.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    final private TestRepository testRepository;

    @Transactional
    public List<Test> getAllTest() {
        return testRepository.findAll();
    }
}
