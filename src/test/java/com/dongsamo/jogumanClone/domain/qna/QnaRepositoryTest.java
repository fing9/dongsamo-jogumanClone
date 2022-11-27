package com.dongsamo.jogumanClone.domain.qna;

import com.dongsamo.jogumanClone.domain.Qna.Qna;
import com.dongsamo.jogumanClone.domain.Qna.QnaRepository;
import com.dongsamo.jogumanClone.domain.notice.Notice;
import com.dongsamo.jogumanClone.domain.user.User;
import com.dongsamo.jogumanClone.domain.user.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QnaRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    QnaRepository qnaRepository;

//    @After
//    public void cleanUp() {
//        qnaRepository.deleteAll();
//    }

    @Test
    public void Qna저장() {
        //given
        User user = userRepository.findByEmail("admin@naver.com").get();

        //when
        qnaRepository.save(Qna.builder()
                .title("제목")
                .content("본문")
                .secret("비밀글")
                .hits(0L)
                .user(user)
                .build());

        //then
        List<Qna> qnaList = qnaRepository.findAll();

        Qna qna = qnaList.get(0);

        System.out.println("User Entity ID : " + user.getId());

        assertThat(qna.getTitle()).isEqualTo("제목");
        assertThat(qna.getContent()).isEqualTo("본문");
        assertThat(qna.getSecret()).isEqualTo("비밀글");
        assertThat(qna.getHits()).isEqualTo(0L);
        assertThat(qna.getUser().getId()).isEqualTo(user.getId());
    }
}
