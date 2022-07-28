package com.dongsamo.jogumanClone.dto;

import com.dongsamo.jogumanClone.domain.product.Product;
import lombok.Builder;
import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private String category;
    private Long price;
    private String description;
    private Long amount;

    @Builder
    public ProductDto(Product product) {
        this.name = product.getName();
        this.category = product.getCategory();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.amount = product.getAmount();
    }
}
