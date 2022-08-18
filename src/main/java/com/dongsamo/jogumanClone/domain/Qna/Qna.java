package com.dongsamo.jogumanClone.domain.Qna;

import com.dongsamo.jogumanClone.domain.date.BaseTimeEntity;
import com.dongsamo.jogumanClone.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Qna extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    //비밀글여부
    private String secret;

    private Long hits;

    @ManyToOne
    @JoinColumn(name="user")
    private User user;

    @Builder
    public Qna(Long id, String title, String content, String secret, Long hits, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.secret = secret;
        this.hits = hits;
        this.user = user;
    }
}
