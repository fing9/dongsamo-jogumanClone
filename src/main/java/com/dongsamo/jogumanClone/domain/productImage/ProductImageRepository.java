package com.dongsamo.jogumanClone.domain.productImage;

import com.dongsamo.jogumanClone.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, String> {
    List<ProductImage> findAll();
}
