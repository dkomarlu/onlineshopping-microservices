package com.einfonet.programmingtechie.orderservice.controller;

import com.einfonet.programmingtechie.orderservice.dto.OrderRequest;
import com.einfonet.programmingtechie.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {

        return orderService.placeOrder(orderRequest);
    }
}
