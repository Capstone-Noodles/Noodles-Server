package capston.noodles;

import capston.noodles.users.security.JwtProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest(classes = NoodlesApplication.class)
public class JwtProviderTest {

    @Autowired
    private  JwtProvider jwtProvider;

    @Test
    public void jwtCreate(){
        System.out.println(jwtProvider.createToken("1",new ArrayList<>()));
    }
}
