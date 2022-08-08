package com.dongsamo.jogumanClone.domain.product;

import com.dongsamo.jogumanClone.dto.ProductDto;
import com.dongsamo.jogumanClone.dto.ProductSimpleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();

//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE Product p SET p.name = :name, p.category = :category, p.price = :price, p.description = :description, p.amount = :amount  WHERE p.id = :id")
//    public int updateById(Long id, ProductDto productDto);
}
