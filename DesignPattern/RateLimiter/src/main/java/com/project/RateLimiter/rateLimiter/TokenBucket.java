package com.project.RateLimiter.rateLimiter;

public class TokenBucket {

	private final int capacity;
	private final double refillRate;
	private double tokens;
	private long lastRefillTime;

	public TokenBucket(int capacity, double refillRate) {
		this.capacity = capacity;
		this.refillRate = refillRate;
		this.tokens = capacity;
		this.lastRefillTime = System.nanoTime();
	}

	public synchronized boolean tryConsume() {
		refill();
		if (tokens < 1) {
			return false;
		}
		tokens--;
		return true;
	}

	private void refill() {
		long now = System.nanoTime();
		double elapsedSeconds = (now - lastRefillTime) / 1_000_000_000.0;
		double tokensToAdd = elapsedSeconds * refillRate;
		tokens = Math.min(capacity, tokens + tokensToAdd);
		lastRefillTime = now;
	}
}