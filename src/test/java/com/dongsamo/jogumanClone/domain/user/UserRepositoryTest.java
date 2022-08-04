package com.dongsamo.jogumanClone.domain.user;

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
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @After
    public void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    public void 멤버생성() { //spring security 사용해서 테스트하기
        //given
        LocalDateTime now = LocalDateTime.of(2022, 8, 1, 0, 0, 0);
        String email = "test@naver.com";
        String name = "HongGilDong";
        String phone = "01012345678";
        String birthday = "1999-04-26";
        Long point = 0L;
        Long totalPrice = 0L;

        userRepository.save(User.builder()
                        .email(email)
                        .name(name)
                        .phone(phone)
                        .birthday(birthday)
                        .point(point)
                        .totalprice(totalPrice)
                        .build());


        //when
        List<User> memberList = userRepository.findAll();

        //then
        User member = memberList.get(0);

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
