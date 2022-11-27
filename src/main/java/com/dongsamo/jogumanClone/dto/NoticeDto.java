package com.dongsamo.jogumanClone.dto;

import com.dongsamo.jogumanClone.domain.notice.Notice;
import com.dongsamo.jogumanClone.domain.user.User;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NoticeDto {

    @NotBlank
    private Long id;

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;

    private String content;

    private Long hits;

    private Long user_id;

    private String user_name;

    private String createdDate;

    private String modifiedDate;

    @Builder
    public NoticeDto (Notice notice) {
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.hits = notice.getHits();
        this.user_id = notice.getUser().getId();
        this.user_name = notice.getUser().getName();
        this.createdDate = notice.getCreatedDate().toString();
        this.modifiedDate = notice.getModifiedDate().toString();
    }
}
