package com.demo.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.demo.orderservice.dto.OrderLineItemDto;
import com.demo.orderservice.dto.OrderRequest;
import com.demo.orderservice.model.Order;
import com.demo.orderservice.model.OrderLineItem;
import com.demo.orderservice.repository.OrderRepository;
import com.demo.inventoryservice.dto.InventoryResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient webClient;

    private OrderLineItem mapDtoToItem(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setQuanity(orderLineItemDto.getQuantity());
        orderLineItem.setSkuCode(orderLineItemDto.getSkuCode());
        return orderLineItem;
    }

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItem = orderRequest.getOrderLineItemDtoList()
                .stream()
                .map(this::mapDtoToItem)
                .toList();
        order.setOrderLineItemList(orderLineItem);

        List<String> skuCodes = order.getOrderLineItemList().stream()
            .map(OrderLineItem::getSkuCode).toList();

        // call inventory service and place order is product  is in stack
        InventoryResponse[] inventoryResponseArray = webClient.get()
                .uri("http://localhost:8082/api/inventory",
                    UriBuilder -> UriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        boolean result = Arrays.stream(inventoryResponseArray)
                    .allMatch(InventoryResponse::isInStock);

        if ( inventoryResponseArray.length != 0 && result ) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }
    }
}
