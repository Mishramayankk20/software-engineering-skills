package com.example.orderservice.service;

import com.example.orderservice.client.CartServiceClient;
import com.example.orderservice.dto.CartDto;
import com.example.orderservice.dto.CreateOrderRequest;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderItem;
import com.example.orderservice.model.OrderStatus;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartServiceClient cartServiceClient; // <-- Feign client, resolved via Consul

    @Transactional
    public Order createOrderFromCart(CreateOrderRequest request) {
        Long userId = request.getUserId();

        // Inter-service call: order-service -> cart-service, over Feign,
        // with the target instance looked up in Consul by "cart-service".
        CartDto cart = cartServiceClient.getCart(userId);

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new IllegalStateException("Cannot create an order: cart is empty for user " + userId);
        }

        Order order = Order.builder()
                .userId(userId)
                .shippingAddress(request.getShippingAddress())
                .status(OrderStatus.CREATED)
                .totalAmount(BigDecimal.ZERO)
                .build();

        BigDecimal total = BigDecimal.ZERO;
        for (CartDto.CartItemDto ci : cart.getItems()) {
            OrderItem item = OrderItem.builder()
                    .productId(ci.getProductId())
                    .productName(ci.getProductName())
                    .quantity(ci.getQuantity())
                    .price(ci.getPrice())
                    .build();
            order.addItem(item);
            total = total.add(ci.getPrice().multiply(BigDecimal.valueOf(ci.getQuantity())));
        }
        order.setTotalAmount(total);

        Order saved = orderRepository.save(order);

        // Best-effort: clear the cart now that the order is placed.
        // The fallback factory swallows failures here so a cart-service
        // hiccup doesn't undo the order we already persisted.
        cartServiceClient.clearCart(userId);

        log.info("Created order {} for user {} with {} item(s), total={}",
                saved.getId(), userId, saved.getItems().size(), saved.getTotalAmount());

        return saved;
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Order not found: " + orderId));
    }

    public List<Order> getOrdersForUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Transactional
    public Order cancelOrder(Long orderId) {
        Order order = getOrder(orderId);
        order.setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }
}
