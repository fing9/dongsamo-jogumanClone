package com.dongsamo.jogumanClone.controller.admin;

import com.dongsamo.jogumanClone.dto.ProductDto;
import com.dongsamo.jogumanClone.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class AdminController {

    private final ProductService productService;

    @PostMapping("/admin/save")
    public String save(ProductDto product) {
        productService.save(product);
        return "admin";
    }

    //change 요청이 왔을 때 파라미터로 받은 id값을 가지고 기존값을 리턴해줌
    @GetMapping("/admin/change")
    public String change(@RequestParam(value="id", required = true)Long id, Model model) {
        ProductDto productDto = productService.findById(id);
        model.addAttribute("productDto", productDto);
        return "admin";
    }
}
