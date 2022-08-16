package com.dongsamo.jogumanClone.domain.ordersproduct;

import com.dongsamo.jogumanClone.domain.date.BaseTimeEntity;
import com.dongsamo.jogumanClone.domain.orders.Orders;
import com.dongsamo.jogumanClone.domain.product.Product;
import com.dongsamo.jogumanClone.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import static javax.persistence.FetchType.LAZY;

@Getter @Setter
@NoArgsConstructor
@Entity
public class OrdersProduct extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long amount;

    private Long price;
    private Long savepoint;

    private Long totalprice;

    private Long totalsavepoint;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="orders_id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name="product") // name은 ProductImage 테이블에서의 칼럼명을 지정하는 것임 Build할때 product객체를 넣으면 자동으로 product객체의 PK가 들어가면서 생성됨
    private Product product; // 대상 테이블은 매핑된 Entity의 오브젝트형을 보고 자동으로 정함 (여기선 Product)

    @Builder
    public OrdersProduct(Long amount, Long price, Long savepoint, Long totalprice, Long totalsavepoint, Orders orders, Product product){
        this.amount = amount;
        this.price = price;
        this.savepoint = savepoint;
        this.totalprice = totalprice;
        this.totalsavepoint = totalsavepoint;
        this.orders = orders;
        this.product = product;
    }

    //==비즈니스 로직==//
    public void cancel() {
        getProduct().addStock(amount);
    }

    //==조회 로직==//

    /**
     * 주문상품 전체 가격 조회
     */
    public Long getTotalPrice() {
        return this.price * getAmount();
    }
}
