package com.dongsamo.jogumanClone.domain.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String category;
    private Long price;
    private String description;
    private Long amount;

    @Builder
    public Product(String name, String category, Long price, String description, Long amount) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.amount = amount;
    }
}
