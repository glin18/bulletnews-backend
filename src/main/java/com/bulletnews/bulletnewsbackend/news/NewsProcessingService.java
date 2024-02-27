package com.bulletnews.bulletnewsbackend.news;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class NewsProcessingService {

    private final NewsApiService newsApiService;

    public void fetchProcessAndSaveNews(){
        String category = "technology";
        Mono<NewsApiResponseDTO> newsApiResponseDto = newsApiService.fetchTopHeadlinesByCategory(category);

        // 1. Make a request to the external API to fetch news data
        // 2. Process the data (scrape additional content, summarize, etc.)
        // 3. Save the processed data to your database
    }

}
