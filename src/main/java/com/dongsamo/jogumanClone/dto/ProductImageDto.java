package com.dongsamo.jogumanClone.dto;

import com.dongsamo.jogumanClone.domain.productImage.ProductImage;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductImageDto {

    @NotBlank
    private String uuid;

    @NotBlank
    private String uploadPath;

    @NotBlank
    private String fileName;

    private Long product_id;

    private String createdDate;

    private String modifiedDate;

    @Builder
    public ProductImageDto(ProductImage productImage) {
        this.uuid = productImage.getUuid();
        this.uploadPath = productImage.getUploadPath();
        this.fileName = productImage.getFileName();
        this.product_id = productImage.getProduct().getId();
        this.createdDate = productImage.getCreatedDate().toString();
        this.modifiedDate = productImage.getModifiedDate().toString();
    }
}
