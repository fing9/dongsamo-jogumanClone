package com.dongsamo.jogumanClone.dto;

import com.dongsamo.jogumanClone.domain.productImage.ProductImage;
import lombok.Builder;
import lombok.Data;

@Data
public class ProductImageDto {
    private String uuid;
    private String uploadPath;
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
