package com.project.RateLimiter.filter;

public interface RateLimiter {
	boolean allowRequest(String clientId);
}
