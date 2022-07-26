package com.dongsamo.jogumanClone.service;

import com.dongsamo.jogumanClone.component.DtoMapper;
import com.dongsamo.jogumanClone.domain.user.User;
import com.dongsamo.jogumanClone.domain.user.UserRepository;
import com.dongsamo.jogumanClone.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final DtoMapper dtoMapper;

    /*
    Spring Security 필수 메소드 구현

    @param email 이메일
    @return UserDetails
    @throws UsernameNotfoundException 유저가 없을 때 예외 발생
     */

    @Transactional
    @Override //기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 UserInfo로 반환 타입 지정 (자동으로 다운 캐스팅됨)
    public User loadUserByUsername(String email) throws UsernameNotFoundException { //시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException((email))); //람다 표현 공부필요
    }

    /*
    회원정보 저장

    @param userDto 회원정보가 들어있는 DTO
    @return 저장되는 회원의 PK
     */
    @Transactional
    public Long save(UserDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDto.setPassword(encoder.encode(userDto.getPassword())); //입력받은 비밀번호를 BCrypt로 암호화한 후에 회원 저장

        return userRepository.save(User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .password(userDto.getPassword())
                .auth(userDto.getAuth())
                .phone(userDto.getPhone())
                .birthday(userDto.getBirthday())
                .point(0L)
                .totalprice(0L)
                .build()).getId();
    }

    @Transactional
    public List<UserDto> findAll() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for(int i=0;i<userList.size();i++) {
            userDtoList.add(dtoMapper.userEntityToDto(userList.get(i)));
        }

        return userDtoList;
    }

    @Transactional
    public UserDto findById(Long id) {
        User user = userRepository.findById(id).get();

        return dtoMapper.userEntityToDto(user);
    }

    @Transactional
    public UserDto updateById(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).get();

        user.update(userDto.getEmail(),
                userDto.getName(),
                userDto.getPassword(),
                userDto.getAuth(),
                userDto.getPhone(),
                userDto.getBirthday(),
                userDto.getPoint(),
                userDto.getTotalprice());

        return dtoMapper.userEntityToDto(user);
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);

        return;
    }
}
