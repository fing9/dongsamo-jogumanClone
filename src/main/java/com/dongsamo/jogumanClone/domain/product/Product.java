package com.dongsamo.jogumanClone.domain.product;

import com.dongsamo.jogumanClone.domain.date.BaseTimeEntity;
import com.dongsamo.jogumanClone.domain.productImage.ProductImage;
import com.dongsamo.jogumanClone.dto.ProductImageDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank(message = "이름은 필수 입력 값입니다.")
    @Column
    private String name;
    //@NotBlank(message = "카테고리는 필수 입력 값입니다.")
    private String category;
    //@NotBlank(message = "가격은 필수 입력 값입니다.")
    private Long price;
    //@Nullable
    private String description;
    //@NotBlank(message = "수량은 필수 입력 값입니다.")
    private Long amount;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<ProductImage> productImages = new ArrayList<>();
//    빌더에는 넣지않는다

    @Builder
    public Product(String name, String category, Long price, String description, Long amount) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.amount = amount;
    }

    public List<ProductImageDto> toDtoList(List<ProductImage> productImageList) {
        List<ProductImageDto> productImageDtoList = new ArrayList<>();

        for(int i=0;i<productImageList.size();i++) {
            productImageDtoList.add(new ProductImageDto(productImageList.get(i)));
        }

        return productImageDtoList;
    }
}
