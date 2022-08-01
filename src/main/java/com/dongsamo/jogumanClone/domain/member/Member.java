package com.dongsamo.jogumanClone.domain.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String email;
    private String name;
    private String phone;
    private String birthday;
    private String role;
    private Long point;
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
