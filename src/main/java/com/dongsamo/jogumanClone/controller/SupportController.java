package com.dongsamo.jogumanClone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SupportController {

    /*
    나중에 model에 게시글 전체 조회해서 포함해서 getmapping해줘야함
     */

    @GetMapping("/support")
    public String notice(Model model) {
        return "supportNotice";
    }

    @GetMapping("/qna")
    public String qna(Model model) {
        return "supportQna";
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
