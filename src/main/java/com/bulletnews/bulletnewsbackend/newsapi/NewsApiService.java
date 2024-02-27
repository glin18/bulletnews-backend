package com.bulletnews.bulletnewsbackend.newsapi;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NewsApiService {

    private final WebClient webClient;
    private final NewsApiConfig newsApiConfig;

    public NewsApiService(WebClient.Builder webClientBuilder, NewsApiConfig newsApiConfig) {
        this.webClient = webClientBuilder.baseUrl(newsApiConfig.getBaseUrl()).build();
        this.newsApiConfig = newsApiConfig;
    }

    public NewsApiResponseDTO fetchTopHeadlinesByCategory(String category) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("")
                        .queryParam("country", newsApiConfig.getCountry())
                        .queryParam("apiKey", newsApiConfig.getApiKey())
                        .queryParam("category", category)
                        .build())
                .retrieve()
                .bodyToMono(NewsApiResponseDTO.class).block();
    }
}