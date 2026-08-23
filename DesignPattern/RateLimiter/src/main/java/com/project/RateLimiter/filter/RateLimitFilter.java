package com.project.RateLimiter.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

	private final RateLimiter rateLimiter;

	public RateLimitFilter(RateLimiter rateLimiter) {
		this.rateLimiter = rateLimiter;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String clientId = request.getRemoteAddr();

		boolean allowed = rateLimiter.allowRequest(clientId);

		if (!allowed) {
			response.setStatus(429);
			response.getWriter().write("Too Many Requests from "+clientId);
			return;
		}

		filterChain.doFilter(request, response);
	}
}