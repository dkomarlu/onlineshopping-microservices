package com.einfonet.programmingtechie.orderservice.service;

import com.einfonet.programmingtechie.orderservice.dto.InventoryResponse;
import com.einfonet.programmingtechie.orderservice.dto.OrderLineItemsDto;
import com.einfonet.programmingtechie.orderservice.dto.OrderRequest;
import com.einfonet.programmingtechie.orderservice.model.Order;
import com.einfonet.programmingtechie.orderservice.model.OrderLineItems;
import com.einfonet.programmingtechie.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClient.Builder webClientBuilder;

    public String placeOrder (OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList =
                orderRequest.getOrderLinteItemsDtoList()
                        .stream()
                        .map(this::mapToOrderLineItems)
                        .toList();
        order.setOrderLineItemsList(orderLineItemsList);

        //Build a list of skucodes from orderlineitemslist to check if they are in stock
        List<String> skuCodes = order.getOrderLineItemsList()
                .stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        //Check if the product in stock by calling Inventory Service isInStock
        InventoryResponse[] inventoryResponseArray = webClientBuilder.build()
                                        .get()
                                        .uri("http://inventory-service/api/inventory",
                                                uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                                        .retrieve()
                                        .bodyToMono(InventoryResponse[].class)
                                        .block();
        assert inventoryResponseArray != null;
        boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
                .allMatch(InventoryResponse::isInStock);

        if (allProductsInStock) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("One or more Products are not in stock, please try again later..");
        }

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
