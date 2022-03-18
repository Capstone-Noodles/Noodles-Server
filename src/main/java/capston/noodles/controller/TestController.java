package capston.noodles.controller;

import capston.noodles.model.Test;
import capston.noodles.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/test")
    public List<Test> getAllTest(){
        System.out.println("hi");
        return testService.getAllTest();

    }

}
