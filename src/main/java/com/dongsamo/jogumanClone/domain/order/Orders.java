package com.dongsamo.jogumanClone.domain.order;


import com.dongsamo.jogumanClone.domain.date.BaseTimeEntity;
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

    @Builder
    public Orders(Long amount, Long payment, String paymentstate, String state, Long userpoint, String paymentway){
        this.amount = amount;
        this.payment = payment;
        this.paymentstate = paymentstate;
        this.state = state;
        this.userpoint = userpoint;
        this.paymentway = paymentway;
    }

}
