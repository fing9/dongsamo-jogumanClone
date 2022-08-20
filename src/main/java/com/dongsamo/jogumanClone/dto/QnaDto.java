package com.dongsamo.jogumanClone.dto;

import com.dongsamo.jogumanClone.domain.Qna.Qna;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class QnaDto {

    @NotBlank
    private Long id;

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;

    private String content;

    private String secret;

    private Long hits;

    private Long user_id;

    private String user_name;

    private String createdDate;

    private String modifiedDate;

    @Builder
    public QnaDto (Qna qna) {
        this.id = qna.getId();
        this.title = qna.getTitle();
        this.content = qna.getContent();
        this.secret = qna.getSecret();
        this.hits = qna.getHits();
        this.user_id = qna.getUser().getId();
        this.user_name = qna.getUser().getName();
        this.createdDate = qna.getCreatedDate().toString();
        this.modifiedDate = qna.getModifiedDate().toString();
    }
}
