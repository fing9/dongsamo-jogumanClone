package com.dongsamo.jogumanClone.domain.member;

import com.dongsamo.jogumanClone.domain.date.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    @NotNull
    private String email;
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @NotNull
    private String name;
    @Nullable
    private String phone;
    @Nullable
    private String birthday;
    @NotNull
    private String role;
    @NotNull
    private Long point;
    @NotNull
    private Long totalprice;

    @Builder
    public Member(String email, String name, String phone, String birthday, String role, Long point, Long totalprice) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
        this.role = role;
        this.point = point;
        this.totalprice = totalprice;
    }
}
