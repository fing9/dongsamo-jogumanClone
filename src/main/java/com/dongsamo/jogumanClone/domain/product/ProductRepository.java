package com.dongsamo.jogumanClone.domain.product;

import com.dongsamo.jogumanClone.dto.ProductSimpleDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();
}
