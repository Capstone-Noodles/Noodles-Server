package capston.noodles.users.security;


import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    @Value("spring.jwt.secret")
    private String secretKey;

    private Long tokenValidMillisecond = 60 * 60 * 1000L;

    private final CustomUserDetailService userDetailService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // Jwt 생성
    public String createToken(String userPk, List<String> roles){
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", roles);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Jwt로 인증정보 조회
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailService.loadUserByUsername(this.getUserPk(token));
        System.out.println("userpk: " + this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"", userDetails.getAuthorities());
    }

    // Jwt 에서 회원 구분 Pk 추출
    public String getUserPk(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // HTTP Request 헤더에서 Token parsing -> "X-AUTH-TOKEN: jwt"
    public String resolveToken(HttpServletRequest request){
        return request.getHeader("X-AUTH-TOKEN");
    }

    // jwt의 유효성 및 만료일자 확인
    public boolean validationToken(String token){
        try{
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date()); // 만료 날짜가 현재보다 이전이면 False
        }catch(Exception e){
            return false;
        }
    }
}
