package com.dongsamo.jogumanClone.controller.admin;

import com.dongsamo.jogumanClone.domain.product.Product;
import com.dongsamo.jogumanClone.domain.product.ProductRepository;
import com.dongsamo.jogumanClone.domain.productImage.ProductImageRepository;
import com.dongsamo.jogumanClone.dto.ProductDto;
import com.dongsamo.jogumanClone.dto.ProductImageDto;
import com.dongsamo.jogumanClone.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@CrossOrigin // 다른 Origin의 데이터를 사용한다면 CORS 표준을 지켜 다른 도메인을 허용하도록 할 수 있게 한다
@RequiredArgsConstructor
@RestController
public class AdminController {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

//    @PostMapping("/api/v1/save")
//    public String save(ProductDto productDto) {
//        productService.save(productDto);
//        return "admin";
//    }

    @PostMapping("/admin/save")
    public String saveProduct( //@Valid로 유효성검사 추가하기 (모든 엔티티 필드에 조건을 붙여야함)
                                          @RequestParam("name") String name,
                                          @RequestParam("category") String category,
                                          @RequestParam("price") Long price,
                                          @RequestParam("description") String description,
                                          @RequestParam("amount") Long amount,
                                          @RequestParam("images") List<MultipartFile> images
    ) throws Exception {
        ProductDto productDto = productService.save(Product.builder()
                .name(name)
                .category(category)
                .price(price)
                .description(description)
                .amount(amount)
                .build(), images);

        //다른 URI로 파싱할수도 있음
        //URI uriLocation = new URI("/board/" + productDto.getId());
        return "redirect:/admin";
    }

    //change 요청이 왔을 때 파라미터로 받은 id값을 가지고 기존값을 리턴해줌
    @GetMapping("/admin/change")
    public String change(@RequestParam(value = "id", required = true) Long id, Model model) {
        ProductDto productDto = productService.findById(id);
        model.addAttribute("productDto", productDto);
        return "redirect:/admin";
    }
}
