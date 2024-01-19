package com.einfonet.programmingtechie.orderservice.service;

import com.einfonet.programmingtechie.orderservice.dto.OrderLineItemsDto;
import com.einfonet.programmingtechie.orderservice.dto.OrderRequest;
import com.einfonet.programmingtechie.orderservice.model.Order;
import com.einfonet.programmingtechie.orderservice.model.OrderLineItems;
import com.einfonet.programmingtechie.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public String placeOrder (OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList =
                orderRequest.getOrderLinteItemsDtoList()
                        .stream()
                        .map(this::mapToOrderLineItems)
                        .toList();
        order.setOrderLineItemsList(orderLineItemsList);
        orderRepository.save(order);
        return "Order is created Succssfully";
    }

    private OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
