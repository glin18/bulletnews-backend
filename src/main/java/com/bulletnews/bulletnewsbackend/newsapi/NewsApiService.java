package com.bulletnews.bulletnewsbackend.newsapi;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NewsApiService {

    private final int PAGE_SIZE = 10;
    private final WebClient webClient;
    private final NewsApiConfig newsApiConfig;

    public NewsApiService(WebClient.Builder webClientBuilder, NewsApiConfig newsApiConfig) {
        this.webClient = webClientBuilder.baseUrl(newsApiConfig.getBaseUrl()).build();
        this.newsApiConfig = newsApiConfig;
    }

    public NewsApiResponse fetchTopHeadlinesByCategory(String category) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("")
                        .queryParam("country", newsApiConfig.getCountry())
                        .queryParam("apiKey", newsApiConfig.getApiKey())
                        .queryParam("category", category)
                        .queryParam("pageSize", PAGE_SIZE)
                        .build())
                .retrieve()
                .bodyToMono(NewsApiResponse.class).block();
    }
}
