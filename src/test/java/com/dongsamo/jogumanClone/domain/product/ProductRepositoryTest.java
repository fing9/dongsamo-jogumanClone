package com.dongsamo.jogumanClone.domain.product;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @After
    public void cleanUp() {
        productRepository.deleteAll();
    }

    @Test
    public void 상품조회() {
        //given
        LocalDateTime now = LocalDateTime.of(2022, 8, 1, 0, 0, 0);
        String name = "브라키오인형";
        String category = "인형";
        Long price = 25000L;
        String description = "소개글";
        Long amount = 100L;

        Product save = productRepository.save(Product.builder()
                .name(name)
                .category(category)
                .price(price)
                .description(description)
                .amount(amount)
                .build());

        //when
        List<Product> productList = productRepository.findAll();

        //then
        Product product = productList.get(0);

        System.out.println(">>>>>>> createDate=" + product.getCreatedDate() + ", modifiedDate=" + product.getModifiedDate());

        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getCategory()).isEqualTo(category);
        assertThat(product.getPrice()).isEqualTo(price);
        assertThat(product.getDescription()).isEqualTo(description);
        assertThat(product.getAmount()).isEqualTo(amount);
        assertThat(product.getCreatedDate().isAfter(now));
        assertThat(product.getModifiedDate().isAfter(now));
    }
}
