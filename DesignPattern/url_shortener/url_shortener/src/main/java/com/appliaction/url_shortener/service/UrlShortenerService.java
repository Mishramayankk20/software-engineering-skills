package com.appliaction.url_shortener.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.appliaction.url_shortener.dto.CreateShortUrlRequest;
import com.appliaction.url_shortener.dto.CreateShortUrlResponse;
import com.appliaction.url_shortener.entity.ShortUrl;
import com.appliaction.url_shortener.repository.ShortUrlRepository;
import com.appliaction.url_shortener.utility.Base62Encoder;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UrlShortenerService {

	private final ShortUrlRepository repository;

	private static final String BASE_URL = "http://localhost:8080/";

	@Transactional
	public CreateShortUrlResponse createShortUrl(CreateShortUrlRequest request) {
		ShortUrl shortUrl = new ShortUrl();
		String shortCode = Base62Encoder.generate(request.url().length());
		shortUrl.setOriginalUrl(request.url());
		shortUrl.setCreatedAt(Instant.now());
//		shortUrl = repository.save(shortUrl);

		shortUrl.setShortCode(shortCode);

		repository.save(shortUrl);
		String shortenedUrl = BASE_URL+shortCode;
		CreateShortUrlResponse response = new CreateShortUrlResponse(shortCode, shortenedUrl, shortUrl.getOriginalUrl());
		return response;
	}

	@Transactional(readOnly = true)
	public String getOriginalUrl(String shortCode) {

		ShortUrl shortUrl = repository.findByShortCode(shortCode)
				.orElseThrow(() -> new RuntimeException("URL not found"));

		return shortUrl.getOriginalUrl();
	}
}