package com.dongsamo.jogumanClone.domain.orders;


import com.dongsamo.jogumanClone.domain.date.BaseTimeEntity;
import com.dongsamo.jogumanClone.domain.delivery.Delivery;
import com.dongsamo.jogumanClone.domain.delivery.DeliveryStatus;
import com.dongsamo.jogumanClone.domain.ordersproduct.OrdersProduct;
import com.dongsamo.jogumanClone.domain.product.Product;
import com.dongsamo.jogumanClone.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter @Setter
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

    @Enumerated(EnumType.STRING)
    private PaymentStatus Status; // 주문상태[ORDER, CANCEL]

    private Long deliverycost;

    private Long usepoint;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;



    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrdersProduct> ordersproducts = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @Builder
    public Orders(Long addressee, String memberAddr1, String memberAddr2, String memberAddr3, Long deliverycost, Long usepoint, User user, OrdersProduct... ordersproducts) {
        this.addressee = addressee;
        this.memberAddr1 = memberAddr1;
        this.memberAddr2 = memberAddr2;
        this.memberAddr3 = memberAddr3;
        this.deliverycost = deliverycost;
        this.usepoint = usepoint;
        for (OrdersProduct ordersproduct : ordersproducts) {
            this.addOrderProduct(ordersproduct);
        }
        this.user = user;
    }

    public void addOrderProduct(OrdersProduct ordersproduct) {
        ordersproducts.add(ordersproduct);
        ordersproduct.setOrders(this);
    }

    //==비즈니스 로직==//
    /**
     * 주문 취소
     */
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        this.setStatus(PaymentStatus.CANCEL);
        for (OrdersProduct ordersproduct : ordersproducts) {
            ordersproduct.cancel();
        }
    }


    //==조회 로직==//
    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrdersProduct ordersproduct : ordersproducts) {
            totalPrice += ordersproduct.getTotalPrice();
        }
        return totalPrice;
    }
}