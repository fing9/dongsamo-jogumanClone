package com.dongsamo.jogumanClone.service;

import com.dongsamo.jogumanClone.domain.delivery.Delivery;
import com.dongsamo.jogumanClone.domain.delivery.DeliveryStatus;
import com.dongsamo.jogumanClone.domain.orders.Orders;
import com.dongsamo.jogumanClone.domain.orders.OrdersRepository;
import com.dongsamo.jogumanClone.domain.ordersproduct.OrdersProduct;
import com.dongsamo.jogumanClone.domain.ordersproduct.OrdersProductRepository;
import com.dongsamo.jogumanClone.domain.product.Product;
import com.dongsamo.jogumanClone.domain.product.ProductRepository;
import com.dongsamo.jogumanClone.domain.user.User;
import com.dongsamo.jogumanClone.domain.user.UserRepository;
import com.dongsamo.jogumanClone.dto.OrdersProductDto;
import com.dongsamo.jogumanClone.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersProductRepository ordersProductRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long UserId, Long ProductId, int amount) {

        //엔티티 조회
        User user = UserRepository.findOne(UserId);
        Product product = productRepository.findOne(ProductId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress();
        delivery.setStatus(DeliveryStatus.READY);

        //주문상품 생성
        OrdersProduct ordersProduct = OrdersProduct.builder()
                .product(OrdersProductDto.getProduct())
                .product.(OrdersProductDto.getPrice()
                .amount(OrdersProduct.getAmount()).
                build();



        //주문 생성
        Orders orders = Orders.createOrder(user, delivery, ordersProduct);

        //주문 저장
        ordersRepository.save(orders);

        return orders.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Orders orders = OrdersRepository.findOne(orderId);
        //주문 취소
        orders.cancel();
    }

}
