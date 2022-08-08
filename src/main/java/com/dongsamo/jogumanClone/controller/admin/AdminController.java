package com.dongsamo.jogumanClone.controller.admin;

import com.dongsamo.jogumanClone.component.ValidateHandler;
import com.dongsamo.jogumanClone.domain.product.Product;
import com.dongsamo.jogumanClone.domain.product.ProductRepository;
import com.dongsamo.jogumanClone.domain.productImage.ProductImageRepository;
import com.dongsamo.jogumanClone.dto.ProductDto;
import com.dongsamo.jogumanClone.dto.ProductImageDto;
import com.dongsamo.jogumanClone.dto.ProductSimpleDto;
import com.dongsamo.jogumanClone.dto.ProductVo;
import com.dongsamo.jogumanClone.network.exception.DuplicateEmailException;
import com.dongsamo.jogumanClone.network.exception.SendEmailException;
import com.dongsamo.jogumanClone.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin // 다른 Origin의 데이터를 사용한다면 CORS 표준을 지켜 다른 도메인을 허용하도록 할 수 있게 한다
@RequiredArgsConstructor
//@Validated
@RestController
public class AdminController {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    private final ValidateHandler validateHandler;

//    @PostMapping("/api/v1/save")
//    public String save(ProductDto productDto) {
//        productService.save(productDto);
//        return "admin";
//    }

    @PostMapping("/admin")
    public Model saveProduct( //@Valid로 유효성검사 추가하기 (모든 엔티티 필드에 조건을 붙여야함)
                               @Valid ProductVo productVo,
                               BindingResult errors,
                               Model model
    ) throws Exception {

        if (errors.hasErrors()) {
            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = validateHandler.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            // 데이터 갱신을 위한 임시 코드
            List<ProductSimpleDto> productSimpleDtoList = productService.findSimpleAll();
            model.addAttribute("productSimpleList", productSimpleDtoList);

            return model;//"redirect:/admin";
        }

        ProductDto productDto = productService.save(Product.builder()
                .name(productVo.getName())
                .category(productVo.getCategory())
                .price(productVo.getPrice())
                .description(productVo.getDescription())
                .amount(productVo.getAmount())
                .build(), productVo.getImages());

        // 데이터 갱신을 위한 임시 코드
        List<ProductSimpleDto> productSimpleDtoList = productService.findSimpleAll();
        model.addAttribute("productSimpleList", productSimpleDtoList);

        //다른 URI로 파싱할수도 있음
        return model;//"redirect:/admin";
    }

    //change 요청이 왔을 때 파라미터로 받은 id값을 가지고 기존값을 리턴해줌
    @GetMapping("/admin/change")
    public Model change(@RequestParam(value = "id", required = true) Long id, Model model) {
        ProductDto productDto = productService.findById(id);
        model.addAttribute("productDto", productDto);
        return model;
    }

    //만들다가 말았음
    @PostMapping("/admin/change")
    public RedirectView change(//@Valid로 유효성검사 추가하기 (모든 엔티티 필드에 조건을 붙여야함)
                               @Valid Long id,
                               @Valid ProductVo productVo,
                               BindingResult errors,
                               Model model
    ) throws Exception {

        if (errors.hasErrors()) {
            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = validateHandler.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            // 데이터 갱신을 위한 임시 코드
            List<ProductSimpleDto> productSimpleDtoList = productService.findSimpleAll();
            model.addAttribute("productSimpleList", productSimpleDtoList);

            return new RedirectView("/admin"); //업데이트에서는 리다이렉션이 아니라 모델을 반환해주는게 맞을듯....?! 리다이렉션은 GetMapping("/admin/change")인경우 admin으로 이어줘서 해결하자
        }

        //productService.updateById(productDto.getId(), productDto, );

        return new RedirectView("/admin");
    }
}
