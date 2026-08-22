package com.appliaction.url_shortener.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.appliaction.url_shortener.dto.CreateShortUrlRequest;
import com.appliaction.url_shortener.dto.CreateShortUrlResponse;
import com.appliaction.url_shortener.service.UrlShortenerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UrlController {

	private final UrlShortenerService service;

	@PostMapping("/api/v1/urls")
	public CreateShortUrlResponse createShortUrl(@RequestBody CreateShortUrlRequest request) {

		return service.createShortUrl(request);
	}

	@GetMapping("/{shortCode}")
	public ResponseEntity<Void> redirect(@PathVariable String shortCode) {

		String originalUrl = service.getOriginalUrl(shortCode);

		return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build();
	}
}