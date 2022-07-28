package com.dongsamo.jogumanClone.dto;

import com.dongsamo.jogumanClone.domain.product.Product;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@NonNull
@Data
public class ProductSimpleDto {
    private String name;
    private String category;
    //private String image;  //(이미지 조회해서 넣어주는것까지 해야함)
    private Long price;
    private Long amount;

    @Builder
    public ProductSimpleDto(Product product) {
        this.name = product.getName();
        this.category = product.getCategory();
        //this.image = ;
        this.price = product.getPrice();
        this.amount = product.getAmount();
    }
}
