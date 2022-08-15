package com.dongsamo.jogumanClone.domain.orders;


import com.dongsamo.jogumanClone.domain.date.BaseTimeEntity;
import com.dongsamo.jogumanClone.domain.product.Product;
import com.dongsamo.jogumanClone.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Orders extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long addressee;

    private String memberAddr1;

    private String memberAddr2;

    private String memberAddr3;

    private String paymentstate;

    private Long deliverycost;

    private Long usepoint;

    @ManyToOne
    @JoinColumn(name="product")
    private Product product;

    @ManyToOne
    @JoinColumn(name="user")
    private User user;

    @Builder
    public Orders(Long addressee, String memberAddr1, String memberAddr2, String memberAddr3, String paymentstate, Long deliverycost, Long usepoint, Product product, User user){
        this.addressee = addressee;
        this.memberAddr1 = memberAddr1;
        this.memberAddr2 = memberAddr2;
        this.memberAddr3 = memberAddr3;
        this.paymentstate = paymentstate;
        this.deliverycost = deliverycost;
        this.usepoint = usepoint;
        this.product = product;
        this.user = user;
    }
}
