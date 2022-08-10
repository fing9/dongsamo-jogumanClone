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

    private Long OrdersProduct_id;
    private Long user_id;



//    User와 OrdersProduct를 외래키로 엮어야함
//    @ManyToOne
//    @JoinColumn(name="User")
//    private User user;

    @Builder
    public Orders(Long addressee, String memberAddr1, String memberAddr2, String memberAddr3, String paymentstate, Long deliverycost, Long usepoint, Long ordersproduct_id, Long user_id){
        this.addressee = addressee;
        this.memberAddr1 = memberAddr1;
        this.memberAddr2 = memberAddr2;
        this.memberAddr3 = memberAddr3;
        this.paymentstate = paymentstate;
        this.deliverycost = deliverycost;
        this.usepoint = usepoint;
//        this.ordersproduct_id = product;
//        this.user = user;
    }
}
