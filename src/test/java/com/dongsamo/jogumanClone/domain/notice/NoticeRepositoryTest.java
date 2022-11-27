package com.dongsamo.jogumanClone.domain.notice;

import com.dongsamo.jogumanClone.domain.user.User;
import com.dongsamo.jogumanClone.domain.user.UserRepository;
import lombok.Cleanup;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeRepositoryTest {

    @Autowired
    NoticeRepository noticeRepository;

    @Autowired
    UserRepository userRepository;

    @After
    public void cleanUp() {
        noticeRepository.deleteAll();
    }

    @Test
    public void 공지저장() {
        //given
        User user = userRepository.findByEmail("admin@naver.com").get();

        //when
        noticeRepository.save(Notice.builder()
                .title("제목")
                .content("본문")
                .hits(0L)
                .user(user)
                .build());

        //then
        List<Notice> noticeList = noticeRepository.findAll();

        Notice notice = noticeList.get(0);

        System.out.println("User Entity ID : " + user.getId());

        assertThat(notice.getTitle()).isEqualTo("제목");
        assertThat(notice.getContent()).isEqualTo("본문");
        assertThat(notice.getHits()).isEqualTo(0L);
        assertThat(notice.getUser().getId()).isEqualTo(user.getId());
    }

}
