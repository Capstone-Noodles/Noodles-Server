package capston.noodles.config;

import capston.noodles.users.security.handler.CustomAccessDeniedHandler;
import capston.noodles.users.security.handler.CustomAuthenticationEntryPoint;
import capston.noodles.users.security.JwtAuthenticationFilter;
import capston.noodles.users.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtProvider jwtProvider;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
//                .antMatchers("/*/login", "/*/signup").permitAll()
                .antMatchers("/users/login").permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.GET, "/exception/**").permitAll()
                .antMatchers(HttpMethod.POST, "/reissue").permitAll()
                .anyRequest().hasRole("USER")
                .and()
                // 접근권한이 없을 때 처리 ex) no jwt or wrong jwt
                .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()
                // 접근권한이 부족할 때 처리 ex) admin이 가능할 때 user가 요청
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
    }
}
