package com.dongsamo.jogumanClone.dto;

import com.dongsamo.jogumanClone.domain.orders.Orders;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class OrdersDto {

    @NotNull
    private Long id;

    private Long addressee;

    private String memberAddr1;

    private String memberAddr2;

    private String memberAddr3;

    private String paymentstate;

    private Long deliverycost;

    private Long usepoint;

    private Long Ordersproduct_id;
    private Long user_id;

    private String createdDate;
    private String modifiedDate;

    @Builder
    public OrdersDto(Orders orders) {
        this.id = orders.getId();
        this.addressee = orders.getAddressee();
        this.memberAddr1 = orders.getMemberAddr1();
        this.memberAddr2 = orders.getMemberAddr2();
        this.memberAddr3 = orders.getMemberAddr3();
        this.paymentstate = orders.getPaymentstate();
        this.deliverycost = orders.getDeliverycost();
        this.usepoint = orders.getUsepoint();
//        this.Ordersproduct_id = orders.getOrdersProduct().getId(); // 주문상품과 외래키 연결해야함
//        this.user_id = orders.getUser().getId(); // 회원과 외래키로 연결해야함
        this.createdDate = orders.getCreatedDate().toString();
        this.modifiedDate = orders.getModifiedDate().toString();
    }
}
