package com.dongsamo.jogumanClone.controller;

import com.dongsamo.jogumanClone.dto.ProductSimpleDto;
import com.dongsamo.jogumanClone.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {

    ProductService productService;

    @GetMapping("")
    public String main() {
        return "main";
    }

    @GetMapping("about")
    public String about() {
        return "abouts";
    }

    @GetMapping("admin")
    public String admin(Model model) {
        List<ProductSimpleDto> productSimpleDtoList = productService.findSimpleAll(new ArrayList<ProductSimpleDto>());
        model.addAttribute("productSummaryList", productSimpleDtoList);
        return "admin";
    }
}
