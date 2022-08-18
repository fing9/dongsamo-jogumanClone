package com.dongsamo.jogumanClone.domain.notice;

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
public class Notice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private Long hits;

    @ManyToOne
    @JoinColumn(name="user")
    private User user;

    @Builder
    public Notice(Long id, String title, String content, Long hits, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.user = user;
    }
}
