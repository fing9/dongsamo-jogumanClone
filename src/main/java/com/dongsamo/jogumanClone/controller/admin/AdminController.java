package com.dongsamo.jogumanClone.controller.admin;

import com.dongsamo.jogumanClone.dto.ProductDto;
import com.dongsamo.jogumanClone.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AdminController {

    private ProductService productService;

    @PostMapping("/save")
    public String save(ProductDto product) {
        productService.save(product);
        return "admin";
    }

}
