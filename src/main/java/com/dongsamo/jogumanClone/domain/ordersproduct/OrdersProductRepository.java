package com.dongsamo.jogumanClone.domain.ordersproduct;

import com.dongsamo.jogumanClone.domain.orders.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

public interface OrdersProductRepository extends JpaRepository<OrdersProduct, Long> {
}
