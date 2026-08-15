package com.example.orderservice.client;

import com.example.orderservice.dto.CartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * name = "cart-service" MUST match spring.application.name in cart-service's
 * application.yml. Spring Cloud Consul resolves that name to a live instance
 * (host:port) at call time via the Consul service registry — no hardcoded URL
 * needed. spring-cloud-starter-loadbalancer picks an instance if there are
 * several running.
 *
 * fallbackFactory provides a graceful response if cart-service is
 * unreachable or the circuit breaker is open.
 */
@FeignClient(
        name = "cart-service",
        fallbackFactory = CartServiceClientFallbackFactory.class
)
public interface CartServiceClient {

    @GetMapping("/api/carts/{userId}")
    CartDto getCart(@PathVariable("userId") Long userId);

    @DeleteMapping("/api/carts/{userId}")
    void clearCart(@PathVariable("userId") Long userId);
}
