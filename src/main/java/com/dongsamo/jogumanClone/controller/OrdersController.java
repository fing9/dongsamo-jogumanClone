package com.dongsamo.jogumanClone.controller;


import com.dongsamo.jogumanClone.service.OrdersService;
import com.dongsamo.jogumanClone.service.ProductService;
import com.dongsamo.jogumanClone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;
    private final UserService userservice;
    private final ProductService itemService;



}
