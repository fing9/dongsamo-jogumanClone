package com.dongsamo.jogumanClone.domain.user;

import com.dongsamo.jogumanClone.domain.date.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    @NotNull
    @Column(unique = true)
    private String email;
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @NotNull
    private String name;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;
    @NotNull
    private String auth;
    @Nullable
    private String phone;
    @Nullable
    private String birthday;
    @NotNull
    private Long point;
    @NotNull
    private Long totalprice;

    @Builder
    public User(String email, String name, String password, String auth, String phone, String birthday, Long point, Long totalprice) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.auth = auth;
        this.phone = phone;
        this.birthday = birthday;
        this.point = point;
        this.totalprice = totalprice;
    }

    //사용자의 권한을 콜렉션 형태로 반환
    //단, 클래스 자료형은 GrantedAuthority를 구현해야함
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : auth.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    //사용자의 id를 반환 (unique한 값)
    @Override
    public String getUsername() {
        return email;
    }

    //사용자의 password를 반환
    @Override
    public String getPassword() {
        return password;
    }

    //계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        //만료되었는지 확인하는 로직
        return true; //true -> 만료되지 않았음
    }

    //계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        //계정 잠금되었는지 확인하는 로직
        return true; //true -> 잠금되지 않았음
    }

    //패스워드의 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        //패스워드가 만료되었는지 확인하는 로직
        return true; //true -> 만료되지 않았음
    }

    //계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        //계정이 사용 가능한지 확인하는 로직
        return true;
    }
}
