package com.dongsamo.jogumanClone.dto;

import com.dongsamo.jogumanClone.domain.productImage.ProductImage;
import com.dongsamo.jogumanClone.domain.product.Product;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@NonNull
@Data
public class ProductSimpleDto {
    private Long id;
    private String name;
    private String category;
    private Long price;
    private Long amount;
    private List<ProductImageDto> images;

    @Builder
    public ProductSimpleDto(Product product, List<ProductImageDto> productImageDtoList) {
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.amount = product.getAmount();
        this.images = productImageDtoList;
    }
}
