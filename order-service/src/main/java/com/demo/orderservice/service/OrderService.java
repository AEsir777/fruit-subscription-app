package com.demo.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.demo.orderservice.dto.OrderLineItemDto;
import com.demo.orderservice.dto.OrderRequest;
import com.demo.orderservice.model.Order;
import com.demo.orderservice.model.OrderLineItem;
import com.demo.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

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

        // call inventory service and place order is product  is in stack
        orderRepository.save(order);
    }
}
