package com.bulletnews.bulletnewsbackend.newsapi;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class NewsApiConfig {

    @Value("${NEWS_BASE_URL}")
    private String baseUrl;

    @Value("${COUNTRY}")
    private String country;

    @Value("${NEWS_API_KEY}")
    private String apiKey;

}
