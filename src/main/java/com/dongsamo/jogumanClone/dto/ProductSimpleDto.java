package com.dongsamo.jogumanClone.dto;

import com.dongsamo.jogumanClone.domain.productImage.ProductImage;
import com.dongsamo.jogumanClone.domain.product.Product;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@NonNull
@Data
public class ProductSimpleDto {
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

    //@PositiveOrZero(message = "0 이상의 수를 입력해야합니다.")
    @Min(value = 0, message = "0 이상의 수를 입력해야합니다.")
    @NotNull(message = "수량은 필수 입력 값입니다.")
    private Long amount;

    @Nullable
    private List<ProductImageDto> images;

    private String createdDate;

    private String modifiedDate;

    @Builder
    public ProductSimpleDto(Product product, List<ProductImageDto> productImageDtoList) {
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.amount = product.getAmount();
        this.images = productImageDtoList;
        this.createdDate = product.getCreatedDate().toString();
        this.modifiedDate = product.getModifiedDate().toString();
    }
}
// 이미지랑 프로덕트 DTO를 분리하거나 각각의 VO를까만들어서 받은다음에 DTO에 집어넣어서 만드는식으로 해야할