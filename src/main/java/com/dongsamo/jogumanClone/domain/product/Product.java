package com.dongsamo.jogumanClone.domain.product;

import com.dongsamo.jogumanClone.domain.date.BaseTimeEntity;
import com.dongsamo.jogumanClone.domain.productImage.ProductImage;
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
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String category;
    @NotNull
    private Long price;
    @Nullable
    private String description;
    @NotNull
    private Long amount;

    @Nullable
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
}
