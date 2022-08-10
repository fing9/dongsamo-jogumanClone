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
//    OrderitemDto
//            변수
//    주문번호 - orderId (외래키)
//    상품번호 - productid (외래키)
//    주문수량 - amount
//    orderItem 기본키 - id
//    상품 한개 가격 - price
//    상품 한 개 구매시 획득 포인트 - savepoint
//    총가격 - totalprice
//    총 획득 포인트 - totalsavePoint
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

    private Long product_id;

    @Builder
    public OrdersProductDto(OrdersProduct ordersproduct){
        this.amount = ordersproduct.getAmount();
        this.price = ordersproduct.getPrice();
        this.savepoint = ordersproduct.getSavepoint();
        this.totalprice = ordersproduct.getTotalprice();
        this.totalsavepoint = ordersproduct.getTotalsavepoint();
        this.product_id = ordersproduct.getProduct().getId();
//        this.user = user; // user id를 외래키로 연결해야함
    }


    public void initSaleTotal() {
        this.totalprice = this.price*this.amount;
        this.savepoint = Long.valueOf((int)(Math.floor(this.price*0.02)));
        this.totalsavepoint =this.savepoint * this.amount;
    }
}