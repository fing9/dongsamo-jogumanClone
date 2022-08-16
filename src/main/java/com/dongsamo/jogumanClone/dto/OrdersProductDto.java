package com.dongsamo.jogumanClone.dto;

import com.dongsamo.jogumanClone.domain.orders.Orders;
import com.dongsamo.jogumanClone.domain.ordersproduct.OrdersProduct;
import com.dongsamo.jogumanClone.domain.product.Product;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class OrdersProductDto {
    @NotNull
    private Long id;

    @Min(value = 0, message = "0 이상의 수를 입력해야합니다.")
    @Positive()
    @NotNull(message = "수량은 필수 입력 값 입니다.")
    private Long amount;

    @Min(value = 0, message = "0 이상의 수를 입력해야합니다.")
    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Long price;

    @NotNull
    private Long savepoint;

    @NotNull
    private Long totalprice;

    @NotNull
    private Long totalsavepoint;

    private Long product;

    @Builder
    public OrdersProductDto(OrdersProduct ordersproduct){
        this.amount = ordersproduct.getAmount();
        this.price = ordersproduct.getPrice();
        this.savepoint = ordersproduct.getSavepoint();
        this.totalprice = ordersproduct.getTotalPrice();
        this.totalsavepoint = ordersproduct.getTotalsavepoint();
        this.product = ordersproduct.getProduct().getId();
    }


    public void initSaleTotal() {
        this.totalprice = this.price*this.amount;
        this.savepoint = Long.valueOf((int)(Math.floor(this.price*0.02)));
        this.totalsavepoint =this.savepoint * this.amount;
    }
}