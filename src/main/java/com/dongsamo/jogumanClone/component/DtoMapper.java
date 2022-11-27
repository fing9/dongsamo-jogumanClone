package com.dongsamo.jogumanClone.component;

import com.dongsamo.jogumanClone.domain.Qna.Qna;
import com.dongsamo.jogumanClone.domain.notice.Notice;
import com.dongsamo.jogumanClone.domain.user.User;
import com.dongsamo.jogumanClone.dto.NoticeDto;
import com.dongsamo.jogumanClone.dto.QnaDto;
import com.dongsamo.jogumanClone.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DtoMapper {

    public UserDto userEntityToDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setAuth(user.getAuth());
        userDto.setPhone(user.getPhone());
        userDto.setBirthday(user.getBirthday());
        userDto.setPoint(user.getPoint());
        userDto.setTotalprice(user.getTotalprice());

        return userDto;
    }

    public List<NoticeDto> noticeEntityListToDtoList(List<Notice> noticeList) {
        List<NoticeDto> noticeDtoList = new ArrayList<>();

        if (noticeList != null && noticeList.size() > 0) {
            for (int i = 0; i < noticeList.size(); i++) {
                noticeDtoList.add(new NoticeDto(noticeList.get(i)));
            }
        }

        return noticeDtoList;
    }

    public List<QnaDto> qnaEntityListToDtoList(List<Qna> qnaList) {
        List<QnaDto> qnaDtoList = new ArrayList<>();

        if(qnaList != null && qnaList.size() > 0) {
            for (int i = 0; i < qnaList.size(); i++) {
                qnaDtoList.add(new QnaDto(qnaList.get(i)));
            }
        }

        return qnaDtoList;
    }
}
