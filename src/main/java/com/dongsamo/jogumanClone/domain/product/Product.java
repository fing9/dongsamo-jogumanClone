package com.dongsamo.jogumanClone.domain.product;

import com.dongsamo.jogumanClone.domain.date.BaseTimeEntity;
import com.dongsamo.jogumanClone.domain.productImage.ProductImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String category;
    private Long price;
    private String description;
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
}
