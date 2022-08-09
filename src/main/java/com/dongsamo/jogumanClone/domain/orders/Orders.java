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
    private Long amount;

    private Long payment;

    private String paymentstate;

    private String state;

    private Long userpoint;

    private String paymentway;

    @ManyToOne
    @JoinColumn(name="product") // name은 ProductImage 테이블에서의 칼럼명을 지정하는 것임 Build할때 product객체를 넣으면 자동으로 product객체의 PK가 들어가면서 생성됨
    private Product product; // 대상 테이블은 매핑된 Entity의 오브젝트형을 보고 자동으로 정함 (여기선 Product)

    @ManyToOne
    @JoinColumn(name="User")
    private User user;

    @Builder
    public Orders(Long amount, Long payment, String paymentstate, String state, Long userpoint, String paymentway, Product product, User user){
        this.amount = amount;
        this.payment = payment;
        this.paymentstate = paymentstate;
        this.state = state;
        this.userpoint = userpoint;
        this.paymentway = paymentway;
        this.product = product;
        this.user = user;
    }
}
