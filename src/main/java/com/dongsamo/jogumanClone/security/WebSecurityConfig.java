package com.dongsamo.jogumanClone.security;

import com.dongsamo.jogumanClone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Override
    public void configure(WebSecurity web) { //인증을 무시할 경로들을 설정한다.
        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**"); //static 하위폴더에는 무조건 접근 가능해야함
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { //http 관련 인증 설정이 가능합니다.
        http.authorizeRequests()
                .antMatchers("/", "/login", "signup", "/user", "/admin").permitAll() //누구나 접근 허용 test에서만 admin허용
                .antMatchers("/order").hasRole("MEMBER") //MEMBER, ADMIN만 접근 가능
                //.antMatchers("/admin").hasRole("ADMIN") //ADMIN만 접근 가능 test단계에서 잠시 주석처리해둠
                .anyRequest().authenticated() //나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
                .and()
                .formLogin() //로그인에 관한 설정을 의미 : loginPage()->로그인 페이지 링크 설정, defaultSuccessUrl()-> 로그인 성공 후 리다이렉트할 주소
                .loginPage("/login") //로그인 페이지 링크
                .defaultSuccessUrl("/") //로그인 성공 후 리다이렉트 주소
                .and()
                .logout() //로그아웃에 관한 설정을 의미 : logoutSuccessUrl()->로그아웃 성공 후 리다이렉트할 주소 , invalidateHttpSesstion()->로그아웃 이후 세션 전체 삭제 여부
                .logoutSuccessUrl("/login") //로그아웃 성공시 리다이렉트 주소
                .invalidateHttpSession(true); //세션 날리기
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception { //로그인할 때 필요한 정보를 가져오는 곳
        auth.userDetailsService(userService) //유저 정보를 가져오는 서비스를 userService으로 지정합니다.
                //패스워드 인코더는 아까 빈으로 등록해놓은 passwordEncoder()를 사용합니다. (BCrypt)
                //해당 서비스(userService)에서는 UserDetailsService를 implements해서
                //loadUserByUsername() 구현해야함 (서비스 참고)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
