package com.example.orderservice.client;

import com.example.orderservice.dto.CartDto;
import com.example.orderservice.exception.CartServiceUnavailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CartServiceClientFallbackFactory implements FallbackFactory<CartServiceClient> {

    @Override
    public CartServiceClient create(Throwable cause) {
        log.warn("cart-service call failed, falling back. Reason: {}", cause.getMessage());

        return new CartServiceClient() {
            @Override
            public CartDto getCart(Long userId) {
                throw new CartServiceUnavailableException(
                        "cart-service is currently unavailable, could not fetch cart for user " + userId, cause);
            }

            @Override
            public void clearCart(Long userId) {
                // Non-critical side effect — log and swallow so order creation
                // (which already succeeded) isn't rolled back just because
                // the cart couldn't be cleared afterward.
                log.warn("Could not clear cart for user {} because cart-service is unavailable", userId);
            }
        };
    }
}
