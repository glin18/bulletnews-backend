package com.bulletnews.bulletnewsbackend.news;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class NewsProcessingService {

    private final NewsApiService newsApiService;

    public void fetchProcessAndSaveNews(){

        // 1. Make a request to the external API to fetch news data
        // 2. Process the data (scrape additional content, summarize, etc.)
        // 3. Save the processed data to your database
    }

    public NewsApiResponseDTO fetchNews(String category){
        NewsApiResponseDTO newsApiResponseDto = newsApiService.fetchTopHeadlinesByCategory(category);
        log.info(newsApiResponseDto.toString());
        return newsApiResponseDto;
    }

}
