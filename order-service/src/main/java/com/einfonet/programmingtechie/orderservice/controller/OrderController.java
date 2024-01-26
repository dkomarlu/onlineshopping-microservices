package com.einfonet.programmingtechie.orderservice.controller;

import com.einfonet.programmingtechie.orderservice.dto.OrderRequest;
import com.einfonet.programmingtechie.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    public String placeOrder(@RequestBody OrderRequest orderRequest) {

        return orderService.placeOrder(orderRequest);
    }

    public String fallbackMethod (OrderRequest orderRequest, RuntimeException runtimeException){
        return "Ooops Something went wrong,  try again later..";
    }
}
