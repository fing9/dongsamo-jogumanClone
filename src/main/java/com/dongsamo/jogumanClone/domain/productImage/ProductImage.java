package com.dongsamo.jogumanClone.domain.productImage;

import com.dongsamo.jogumanClone.domain.date.BaseTimeEntity;
import com.dongsamo.jogumanClone.domain.product.Product;
import com.dongsamo.jogumanClone.dto.ProductImageDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class ProductImage extends BaseTimeEntity {

    //@NotNull
    @Id
    /* uuid */
    private String uuid; //uuid

    //@NotNull
    /* 경로 */
    private String uploadPath; //upload_path
    /* 파일 이름 */
    //@NotNull
    private String fileName; //file_name

    //@Nullable
    @ManyToOne
    @JoinColumn(name="product") // name은 ProductImage 테이블에서의 칼럼명을 지정하는 것임 Build할때 product객체를 넣으면 자동으로 product객체의 PK가 들어가면서 생성됨
    private Product product; // 대상 테이블은 매핑된 Entity의 오브젝트형을 보고 자동으로 정함 (여기선 Product)

    @Builder
    public ProductImage(String uuid, String uploadPath, String fileName, Product product) {
        this.uuid = uuid;
        this.uploadPath = uploadPath;
        this.fileName = fileName;
        this.product = product;
    }

    public List<ProductImageDto> toDtoList(List<ProductImage> productImageList) {
        List<ProductImageDto> productImageDtoList = new ArrayList<>();

        for(int i=0;i<productImageList.size();i++) {
            productImageDtoList.add(new ProductImageDto(productImageList.get(i)));
        }

        return productImageDtoList;
    }
}
