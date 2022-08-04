package com.dongsamo.jogumanClone.dto;

import com.dongsamo.jogumanClone.domain.productImage.ProductImage;
import com.dongsamo.jogumanClone.domain.product.Product;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDto {
    @NotNull
    private Long id;
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;
    @NotBlank(message = "카테고리는 필수 입력 값입니다.")
    private String category;

    //@PositiveOrZero(message = "0 이상의 수를 입력해야합니다.")
    @Min(value = 0, message = "0 이상의 수를 입력해야합니다.")
    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Long price;

    @Nullable
    private String description;

    //@PositiveOrZero(message = "0 이상의 수를 입력해야합니다.")
    @Min(value = 0, message = "0 이상의 수를 입력해야합니다.")
    @Positive()
    @NotNull(message = "수량은 필수 입력 값입니다.")
    private Long amount;
    @Nullable
    private List<ProductImageDto> images;
    private String createdDate;
    private String modifiedDate;

    @Builder
    public ProductDto(Product product, List<ProductImageDto> productImageDtoList) {
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.amount = product.getAmount();
        this.images = productImageDtoList;
        this.createdDate = product.getCreatedDate().toString();
        this.modifiedDate = product.getModifiedDate().toString();
    }

    public List<ProductImageDto> toDtoList(List<ProductImage> productImageList) {
        List<ProductImageDto> productImageDtoList = new ArrayList<>();

        for(int i=0;i<productImageList.size();i++) {
            productImageDtoList.add(new ProductImageDto(productImageList.get(i)));
        }

        return productImageDtoList;
    }
}
