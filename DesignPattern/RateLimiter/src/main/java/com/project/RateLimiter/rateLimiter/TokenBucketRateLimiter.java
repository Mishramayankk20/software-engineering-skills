package com.project.RateLimiter.rateLimiter;


import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import com.project.RateLimiter.filter.RateLimiter;
@Component
public class TokenBucketRateLimiter implements RateLimiter {

    private final ConcurrentMap<String, TokenBucket> buckets =
            new ConcurrentHashMap<>();

    private static final int CAPACITY = 4;
    private static final double REFILL_RATE = 1;

    @Override
    public boolean allowRequest(String clientId) {

        TokenBucket bucket = buckets.computeIfAbsent(
                clientId,
                id -> new TokenBucket(CAPACITY, REFILL_RATE)
        );

        return bucket.tryConsume();
    }
}