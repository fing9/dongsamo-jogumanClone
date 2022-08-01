package com.dongsamo.jogumanClone.service;

import com.dongsamo.jogumanClone.domain.product.Product;
import com.dongsamo.jogumanClone.domain.product.ProductRepository;
import com.dongsamo.jogumanClone.domain.productImage.ProductImage;
import com.dongsamo.jogumanClone.domain.productImage.ProductImageRepository;
import com.dongsamo.jogumanClone.dto.ProductDto;
import com.dongsamo.jogumanClone.dto.ProductImageDto;
import com.dongsamo.jogumanClone.dto.ProductSimpleDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    ProductService productService;

    @Before
    public void cleanUpBefore() {
        List<Product> productList = productRepository.findAll();

        for(int i=0;i<productList.size();i++) {
            productService.deleteProductImageAndFilesByProductId(productList.get(i).getId());
        }

        productRepository.deleteAll();
        productImageRepository.deleteAll();
    }

    @After
    public void cleanUpAfter() {
        List<Product> productList = productRepository.findAll();

        for(int i=0;i<productList.size();i++) {
            productService.deleteProductImageAndFilesByProductId(productList.get(i).getId());
        }

        productRepository.deleteAll();
        productImageRepository.deleteAll();
    }

    @Test
    public void 상품요약조회() {
        //given
        String name = "브라키오인형";
        String category = "인형";
        Long price = 25000L;
        String description = "소개글";
        Long amount = 100L;

        Product product = Product.builder()
                .name(name)
                .category(category)
                .price(price)
                .description(description)
                .amount(amount)
                .build();

        productRepository.save(product);

        String uuid = "234124-dsfdfs-123124";
        String uploadPath = "/static/image";
        String fileName = "image.png";

        ProductImage productImage = ProductImage.builder()
                .uuid(uuid)
                .uploadPath(uploadPath)
                .fileName(fileName)
                .product(product)
                .build();

        productImageRepository.save(productImage);

        //when
        List<ProductSimpleDto> productSimpleDtoList = productService.findSimpleAll();

        //then
        ProductSimpleDto productSimpleDto = productSimpleDtoList.get(0);
        assertThat(productSimpleDto.getName()).isEqualTo(name);
        assertThat(productSimpleDto.getCategory()).isEqualTo(category);
        assertThat(productSimpleDto.getPrice()).isEqualTo(price);
        assertThat(productSimpleDto.getAmount()).isEqualTo(amount);
    }

    @Test
    public void 상품요약조회_상품이없는경우() {
        //given
        //nothing

        //when
        List<ProductSimpleDto> productSimpleDtoList = productService.findSimpleAll();

        //then
        assertThat(productSimpleDtoList).isNotNull(); //null이 아닌 빈 객체를 반환
    }
}
