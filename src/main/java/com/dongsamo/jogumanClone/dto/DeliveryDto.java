package com.dongsamo.jogumanClone.dto;

import com.dongsamo.jogumanClone.domain.delivery.DeliveryStatus;
import com.dongsamo.jogumanClone.domain.orders.Orders;
import lombok.Data;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DeliveryDto {

    @NotNull
    private Long id;

    @NotBlank(message = "주소는 필수 입력 값입니다.")
    private String address;

    @NotBlank(message = "수신인은 핋수 입력 값입니다.")
    private String receiver;

    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    private String phone;

    @Nullable
    private String note;

    private DeliveryStatus status;

    private String createdDate;

    private String modifiedDate;
}
