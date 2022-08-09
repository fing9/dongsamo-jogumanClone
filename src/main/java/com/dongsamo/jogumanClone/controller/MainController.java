package com.dongsamo.jogumanClone.controller;

import com.dongsamo.jogumanClone.dto.ProductSimpleDto;
import com.dongsamo.jogumanClone.dto.ProductVo;
import com.dongsamo.jogumanClone.dto.UserDto;
import com.dongsamo.jogumanClone.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ProductService productService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping(value = {"/user", "/login"})
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin(@ModelAttribute("productVo") ProductVo productVo, Model model) {
        List<ProductSimpleDto> productSimpleDtoList = productService.findSimpleAll();
        model.addAttribute("productSimpleList", productSimpleDtoList);
        return "admin";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/signupSub")
    public String signupSub(@ModelAttribute("userDto") UserDto userDto, Model model) {
        return "signUpSub";
    }

    @GetMapping("/myPage")
    public String mypage() {
        return "myPage";
    }
}
