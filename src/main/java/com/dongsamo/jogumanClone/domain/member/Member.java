package com.dongsamo.jogumanClone.domain.member;

import com.dongsamo.jogumanClone.domain.date.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String email;
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
