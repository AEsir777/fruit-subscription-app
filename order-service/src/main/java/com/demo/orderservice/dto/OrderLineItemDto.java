package com.demo.orderservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemDto {
    private Long iid;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
