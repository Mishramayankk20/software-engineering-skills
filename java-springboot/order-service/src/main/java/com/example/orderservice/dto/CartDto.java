package com.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * Mirrors the JSON shape returned by cart-service's CartController.
 * Kept as a plain DTO here (order-service does NOT share a JPA entity
 * with cart-service — that would couple the two services' schemas).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private Long id;
    private Long userId;
    private List<CartItemDto> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartItemDto {
        private Long id;
        private Long productId;
        private String productName;
        private Integer quantity;
        private BigDecimal price;
    }
}
