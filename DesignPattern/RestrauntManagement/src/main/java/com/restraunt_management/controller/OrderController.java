package com.restraunt_management.controller;


import org.springframework.web.bind.annotation.*;

import com.restraunt_management.model.OrderStatus;
import com.restraunt_management.model.RestrauntOrder;
import com.restraunt_management.service.OrderService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public RestrauntOrder createOrder(
            @RequestParam int tableNumber,
            @RequestParam String customerName,
            @RequestBody List<Long> menuItemIds) {

        return orderService.createOrder(
                tableNumber,
                customerName,
                menuItemIds,
                null
        );
    }

    @GetMapping("/{id}")
    public RestrauntOrder getOrder(
            @PathVariable Long id) {

        return orderService.getOrder(id);
    }

    @PutMapping("/{id}/status")
    public RestrauntOrder updateStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status) {

        return orderService.updateStatus(
                id,
                status
        );
    }

    @GetMapping("/{id}/bill")
    public Map<String, Object> calculateBill(
            @PathVariable Long id,
            @RequestParam(defaultValue = "NONE")
            String discountType) {

    	RestrauntOrder order =
                orderService.getOrder(id);

        double subtotal =
                order.getSubtotal();

        double finalAmount =
                orderService.calculateBill(
                        id,
                        discountType
                );

        return Map.of(
                "orderId", id,
                "subtotal", subtotal,
                "discountType", discountType,
                "finalAmount", finalAmount
        );
    }
}