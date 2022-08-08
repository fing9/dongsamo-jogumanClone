package com.dongsamo.jogumanClone.dto;

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

    @Min(value = 0, message = "0 이상의 수를 입력해야합니다.")
    @Positive()
    @NotNull(message = "수량은 필수 입력 값 입니다.")
    private Long amount;

    @Min(value = 0, message = "0이상의 수를 입력해야합니다.")
    @NotNull(message = "결제금액은 필수 입력 값 입니다.")
    private Long payment;

    @NotBlank(message = "결제상태는 필수 입력 값 입니다.")
    private String paymentstate;

    @NotBlank(message = "주문상태는 필수 입력 값 입니다.")
    private String state;

    @NotNull(message = "사용포인트는 필수 입력 값 입니다.")
    private Long userpoint;

    @NotBlank(message = "결제방식은 필수 입력 값 입니다.")
    private String paymentway;

    private String createdDate;
    private String modifiedDate;

    public OrdersDto() {

    }
}
