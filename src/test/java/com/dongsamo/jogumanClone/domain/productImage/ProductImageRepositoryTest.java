package com.dongsamo.jogumanClone.domain.productImage;

import com.dongsamo.jogumanClone.domain.product.Product;
import com.dongsamo.jogumanClone.domain.product.ProductRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductImageRepositoryTest {

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    ProductRepository productRepository;

    @Before
    public void setUP() {
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
    }

    @After
    public void cleanUp() {
        productImageRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void 이미지생성() {
        ProductImage productImage = productImageRepository.findAll().get(0);
        LocalDateTime now = LocalDateTime.of(2022, 8, 1, 0, 0, 0);
        String uuid = productImage.getUuid();
        String uploadPath = productImage.getUploadPath();
        String fileName = productImage.getFileName();
        assertThat(uuid).isEqualTo("234124-dsfdfs-123124");
        assertThat(uploadPath).isEqualTo("/static/image");
        assertThat(fileName).isEqualTo("image.png");

        Product product = productImage.getProduct();

        System.out.println(">>>>>>> createDate=" + product.getCreatedDate() + ", modifiedDate=" + product.getModifiedDate());

        assertThat(product.getName()).isEqualTo("브라키오인형");
        assertThat(product.getCategory()).isEqualTo("인형");
        assertThat(product.getPrice()).isEqualTo(25000L);
        assertThat(product.getDescription()).isEqualTo("소개글");
        assertThat(product.getAmount()).isEqualTo(100L);
        assertThat(product.getCreatedDate().isAfter(now));
        assertThat(product.getModifiedDate().isAfter(now));
    }

}
