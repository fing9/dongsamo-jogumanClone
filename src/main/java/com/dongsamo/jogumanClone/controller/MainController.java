package com.dongsamo.jogumanClone.controller;

import com.dongsamo.jogumanClone.dto.ProductDto;
import com.dongsamo.jogumanClone.dto.ProductSimpleDto;
import com.dongsamo.jogumanClone.dto.ProductVo;
import com.dongsamo.jogumanClone.dto.UserDto;
import com.dongsamo.jogumanClone.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String admin(@RequestParam(value = "id", required = false) Long id, Model model) {
        ProductVo productVo = new ProductVo();
        List<ProductSimpleDto> productSimpleDtoList = productService.findSimpleAll();
        model.addAttribute("productSimpleList", productSimpleDtoList);

        //id값이 /admin으로 들어올 경우 여기서 찾아서 프론트로 보냄
        if(id != null) {
            ProductDto productDto = productService.findById(id);
            productVo.setId(productDto.getId());
            productVo.setAmount(productDto.getAmount());
            productVo.setCategory(productDto.getCategory());
            productVo.setDescription(productDto.getDescription());
            productVo.setPrice(productDto.getPrice());
            productVo.setName(productDto.getName());
        }

        model.addAttribute("productVo", productVo);
//        System.out.println("product id ; " + productVo.getId());
        return "/admin";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/signupSub")
    public String signupSub(@ModelAttribute("userDto") UserDto userDto, Model model) {
        return "signUpSub";
    }

    @GetMapping("/store")
    public String store(@ModelAttribute("productVo") ProductVo productVo, Model model) {
        List<ProductSimpleDto> productSimpleDtoList = productService.findSimpleAll();
        model.addAttribute("productSimpleList", productSimpleDtoList);
        return "store";
    }

    @GetMapping("/basket")
    public String basket() {
        return "basket";
    }
}
