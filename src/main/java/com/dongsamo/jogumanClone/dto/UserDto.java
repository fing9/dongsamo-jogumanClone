package com.dongsamo.jogumanClone.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDto {

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
}
