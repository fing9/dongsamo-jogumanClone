package com.dongsamo.jogumanClone.domain.delivery;

import com.dongsamo.jogumanClone.domain.orders.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String address;

    private String receiver;

    private String phone;

    private String note;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Orders orders;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //READY, COMP
}
