package com.dongsamo.jogumanClone.dto;

import com.dongsamo.jogumanClone.domain.productImage.ProductImage;
import com.dongsamo.jogumanClone.domain.product.Product;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String category;
    private Long price;
    private String description;
    private Long amount;
    private List<ProductImageDto> images;

    @Builder
    public ProductDto(Product product, List<ProductImageDto> productImageDtoList) {
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.amount = product.getAmount();
        this.images = productImageDtoList;
    }
}
