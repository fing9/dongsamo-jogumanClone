package com.dongsamo.jogumanClone.component;

import com.dongsamo.jogumanClone.domain.user.User;
import com.dongsamo.jogumanClone.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    public UserDto userEntityToDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setAuth(user.getAuth());
        userDto.setPhone(user.getPhone());
        userDto.setBirthday(user.getBirthday());
        userDto.setPoint(user.getPoint());
        userDto.setTotalprice(user.getTotalprice());

        return userDto;
    }

}
