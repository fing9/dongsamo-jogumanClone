package com.dongsamo.jogumanClone.service;

import com.dongsamo.jogumanClone.domain.product.Product;
import com.dongsamo.jogumanClone.domain.product.ProductRepository;
import com.dongsamo.jogumanClone.dto.ProductSimpleDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @After
    public void cleanUp() {
        productRepository.deleteAll();
    }

    @Test
    public void 상품요약조회() {
        //given
        String name = "브라키오인형";
        String category = "인형";
        Long price = 25000L;
        String description = "소개글";
        Long amount = 100L;

        productRepository.save(Product.builder()
                .name(name)
                .category(category)
                .price(price)
                .description(description)
                .amount(amount)
                .build());

        //when
        List<ProductSimpleDto> productSimpleDtoList = productService.getAll(new ArrayList<ProductSimpleDto>());

        //then
        ProductSimpleDto productSimpleDto = productSimpleDtoList.get(0);
        assertThat(productSimpleDto.getName()).isEqualTo(name);
        assertThat(productSimpleDto.getCategory()).isEqualTo(category);
        assertThat(productSimpleDto.getPrice()).isEqualTo(price);
        assertThat(productSimpleDto.getAmount()).isEqualTo(amount);
    }

}
