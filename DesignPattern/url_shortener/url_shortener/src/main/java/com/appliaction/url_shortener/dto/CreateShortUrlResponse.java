package com.appliaction.url_shortener.dto;

public record CreateShortUrlResponse(String shortCode, String shortUrl, String originalUrl) {
}