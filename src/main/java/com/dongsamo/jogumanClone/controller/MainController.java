package com.dongsamo.jogumanClone.controller;

import com.dongsamo.jogumanClone.dto.ProductSimpleDto;
import com.dongsamo.jogumanClone.service.ProductService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
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

    @GetMapping("/admin")
    public String admin(Model model) {
        List<ProductSimpleDto> productSimpleDtoList = productService.findSimpleAll();
        model.addAttribute("productSimpleList", productSimpleDtoList);
        return "admin";
    }
}
