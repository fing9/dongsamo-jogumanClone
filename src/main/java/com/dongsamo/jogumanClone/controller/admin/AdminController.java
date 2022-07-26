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
import org.springframework.web.servlet.ModelAndView;
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

    @PostMapping("/admin/save")
    public ModelAndView saveProduct( //@Valid로 유효성검사 추가하기 (모든 엔티티 필드에 조건을 붙여야함)
                                     @Valid ProductVo productVo,
                                     BindingResult errors,
                                     Model model
    ) throws Exception {

        ModelAndView mv = new ModelAndView();

        if (errors.hasErrors()) {
            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = validateHandler.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                mv.addObject(key, validatorResult.get(key));
                //model.addAttribute(key, validatorResult.get(key));
            }

            // 데이터 갱신을 위한 임시 코드
            List<ProductSimpleDto> productSimpleDtoList = productService.findSimpleAll();
            mv.addObject("productSimpleList", productSimpleDtoList);
            //model.addAttribute("productSimpleList", productSimpleDtoList);

            mv.setViewName("admin");//setView(new RedirectView("/admin"));

            return mv;
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
        mv.addObject("productSimpleList", productSimpleDtoList);
        //model.addAttribute("productSimpleList", productSimpleDtoList);

        mv.setView(new RedirectView("/admin"));

        //다른 URI로 파싱할수도 있음
        return mv;
    }

    //change 요청이 왔을 때 파라미터로 받은 id값을 가지고 기존값을 리턴해줌
    @GetMapping("/admin/update")
    public ProductVo update(@RequestParam(value = "id", required = true) Long id) {

        ProductVo productVo = new ProductVo();
        ProductDto productDto = productService.findById(id);
        productVo.setId(productDto.getId());
        productVo.setAmount(productDto.getAmount());
        productVo.setCategory(productDto.getCategory());
        productVo.setDescription(productDto.getDescription());
        productVo.setPrice(productDto.getPrice());
        productVo.setName(productDto.getName());

        return productVo;//mv;
    }


    @PostMapping("/admin/update")
    public ModelAndView update(//@Valid로 유효성검사 추가하기 (모든 엔티티 필드에 조건을 붙여야함)
                               @Valid ProductVo productVo,
                               BindingResult errors
    ) throws Exception {
        ModelAndView mv = new ModelAndView();

        if (errors.hasErrors()) {
            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = validateHandler.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                //model.addAttribute(key, validatorResult.get(key));
                mv.addObject(key, validatorResult.get(key));
            }

            // 데이터 갱신을 위한 임시 코드
            List<ProductSimpleDto> productSimpleDtoList = productService.findSimpleAll();
            //model.addAttribute("productSimpleList", productSimpleDtoList);
            mv.addObject("productSimpleList", productSimpleDtoList);

            mv.setViewName("admin");//setView(new RedirectView("/admin"));

            return mv; //업데이트에서는 리다이렉션이 아니라 모델을 반환해주는게 맞을듯....?! 리다이렉션은 GetMapping("/admin/change")인경우 admin으로 이어줘서 해결하자
        }

        ProductVo productVoRet = productService.updateById(productVo.getId(), productVo, productVo.getImages());
        mv.addObject("productVo", productVoRet);
        //model.addAttribute("productVo", productVoRet);

        mv.setView(new RedirectView("/admin"));

        return mv;
    }

    @PostMapping("/admin/delete")
    public ModelAndView delete(@RequestParam("id")Long id) {
        ModelAndView mv = new ModelAndView();

        mv.setView(new RedirectView("/admin"));

        productService.deleteById(id);

        return mv;
    }

}
