package com.dongsamo.jogumanClone.dto;

import com.dongsamo.jogumanClone.domain.orders.Orders;
import com.dongsamo.jogumanClone.domain.orders.PaymentStatus;
import com.dongsamo.jogumanClone.domain.ordersproduct.OrdersProduct;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
public class OrdersDto {

    // @NotNUll등의 칼럼 조건은 이 파일 (DTO)에서 써줘야함! 써주세용 ㅎㅎ
    @NotNull
    private Long id;

    @NotBlank(message = "수신인은 필수 입력 값입니다.")
    private Long addressee;

    @NotBlank(message = "우편번호는 필수 입력 값입니다.")
    private String memberAddr1;

    @NotBlank(message = "회원주소는 필수 입력 값입니다.")
    private String memberAddr2;

    @NotBlank(message = "회원상세주소는 필수 입력 값입니다.")
    private String memberAddr3;

    @NotNull
    private PaymentStatus status;

    @NotNull(message = "배달비는 필수 입력 값입니다.")
    private Long deliverycost;

    @NotNull(message = "사용포인트는 필수 입력 값입니다.")
    private Long usepoint;

    private List<OrdersProduct> ordersproducts;
    private Long user_id;

    private Long delivery_id;

    private String createdDate;
    private String modifiedDate;

    @Builder
    public OrdersDto(Orders orders) {
        this.id = orders.getId();
        this.addressee = orders.getAddressee();
        this.memberAddr1 = orders.getMemberAddr1();
        this.memberAddr2 = orders.getMemberAddr2();
        this.memberAddr3 = orders.getMemberAddr3();
        this.status = orders.getStatus();
        this.deliverycost = orders.getDeliverycost();
        this.usepoint = orders.getUsepoint();
        this.ordersproducts = orders.getOrdersproducts();
        this.delivery_id = orders.getDelivery().getId();
        this.user_id = orders.getUser().getId();
        this.createdDate = orders.getCreatedDate().toString();
        this.modifiedDate = orders.getModifiedDate().toString();
    }
}
