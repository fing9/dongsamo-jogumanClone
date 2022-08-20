package com.dongsamo.jogumanClone.controller;

import com.dongsamo.jogumanClone.component.DtoMapper;
import com.dongsamo.jogumanClone.domain.Qna.Qna;
import com.dongsamo.jogumanClone.domain.Qna.QnaRepository;
import com.dongsamo.jogumanClone.domain.notice.Notice;
import com.dongsamo.jogumanClone.domain.notice.NoticeRepository;
import com.dongsamo.jogumanClone.dto.NoticeDto;
import com.dongsamo.jogumanClone.dto.QnaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SupportController {

    private final NoticeRepository noticeRepository;

    private final QnaRepository qnaRepository;

    private final DtoMapper dtoMapper;
    /*
    나중에 model에 게시글 전체 조회해서 포함해서 getmapping해줘야함
     */

    @GetMapping("/support")
    public String notice(Model model) {
        List<Notice> noticeList = noticeRepository.findAll();

        if(noticeList !=  null) {
            List<NoticeDto> noticeDtoList = dtoMapper.noticeEntityListToDtoList(noticeList);

            model.addAttribute("noticeList", noticeDtoList);
        }

        return "supportNotice";
    }

    @GetMapping("/support/{id}")
    public String noticeSub(@PathVariable Long id, Model model) {
        Notice notice = noticeRepository.getById(id);

        //TODO : 추후 "존재하지 않는 글입니다" 예외처리 해보기!
        if(notice == null) {
            return "supportNoticeSub";
        }

        NoticeDto noticeDto = new NoticeDto(notice);

        model.addAttribute("notice", noticeDto);

        return "supportNoticeSub";
    }

    @GetMapping("/qna")
    public String qna(Model model) {
        List<Qna> qnaList = qnaRepository.findAll();

        if(qnaList != null) {
            List<QnaDto> qnaDtoList = dtoMapper.qnaEntityListToDtoList(qnaList);

            model.addAttribute("qnaList", qnaDtoList);
        }

        return "supportQna";
    }

    @GetMapping("/qna/{id}")
    public String qnaSub(@PathVariable Long id, Model model) {
        Qna qna = qnaRepository.getById(id);

        //TODO : 추후 "존재하지 않는 글입니다" 예외처리 해보기!
        if(qna == null) {
            return "supportQnaSub";
        }

        QnaDto qnaDto = new QnaDto(qna);

        model.addAttribute("qna", qnaDto);

        return "supportQnaSub";
    }

    @GetMapping("/adminNotice")
    public String adminNotice(Model model) {
        return "adminNotice";
    }

    @GetMapping("/adminQna")
    public String adminQna(Model model) {
        return "adminQna";
    }
}
