package com.dongsamo.jogumanClone;

import com.dongsamo.jogumanClone.domain.user.UserRepository;
import com.dongsamo.jogumanClone.domain.product.ProductRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private UserRepository userRepository;
    private ProductRepository productRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //<--Bean 등록-->

}
