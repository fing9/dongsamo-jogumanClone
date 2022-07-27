package com.dongsamo.jogumanClone;

import com.dongsamo.jogumanClone.domain.member.MemberRepository;
import com.dongsamo.jogumanClone.domain.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private MemberRepository memberRepository;
    private ProductRepository productRepository;

    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



}
