package com.dongsamo.jogumanClone.dto;

import com.dongsamo.jogumanClone.domain.product.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
public class ProductVo {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "카테고리는 필수 입력 값입니다.")
    private String category;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Long price;

    @Nullable
    private String description;

    @NotNull(message = "수량은 필수 입력 값입니다.")
    private Long amount;

    @Nullable
    private List<MultipartFile> images;

//    @Builder
//    public ProductVo(Product product, List<MultipartFile> multipartFileList) {
//        this.name = product.getName();
//        this.category = product.getCategory();
//        this.description = product.getDescription();
//        this.price = product.getPrice();
//        this.amount = product.getAmount();
//        this.images = multipartFileList;
//    }
//
//    public ProductVo() {
//        this.name = "";
//        this.category = "";
//        this.description = "";
//        this.price = 0L;
//        this.amount = 0L;
//        this.images = null;
//    }
}
