package com.dongsamo.jogumanClone.domain.member;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @After
    public void cleanUp() {
        memberRepository.deleteAll();
    }

    @Test
    public void 멤버생성() {
        //given
        LocalDateTime now = LocalDateTime.of(2022, 8, 1, 0, 0, 0);
        String email = "test@naver.com";
        String name = "HongGilDong";
        String phone = "01012345678";
        String birthday = "1999-04-26";
        String role = "guest";
        Long point = 0L;
        Long totalPrice = 0L;

        memberRepository.save(Member.builder()
                        .email(email)
                        .name(name)
                        .phone(phone)
                        .birthday(birthday)
                        .role(role)
                        .point(point)
                        .totalprice(totalPrice)
                        .build());


        //when
        List<Member> memberList = memberRepository.findAll();

        //then
        Member member = memberList.get(0);

        System.out.println(">>>>>>> createDate=" + member.getCreatedDate() + ", modifiedDate=" + member.getModifiedDate());

        assertThat(member.getEmail()).isEqualTo(email);
        assertThat(member.getName()).isEqualTo(name);
        assertThat(member.getPhone()).isEqualTo(phone);
        assertThat(member.getBirthday()).isEqualTo(birthday);
        assertThat(member.getRole()).isEqualTo(role);
        assertThat(member.getPoint()).isEqualTo(point);
        assertThat(member.getTotalprice()).isEqualTo(totalPrice);
        assertThat(member.getCreatedDate().isAfter(now));
        assertThat(member.getModifiedDate().isAfter(now));
    }

}
