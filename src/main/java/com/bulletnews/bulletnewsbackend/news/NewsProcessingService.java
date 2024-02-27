package com.bulletnews.bulletnewsbackend.news;

import com.bulletnews.bulletnewsbackend.newsapi.NewsApiResponseDTO;
import com.bulletnews.bulletnewsbackend.newsapi.NewsApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class NewsProcessingService {

    private final NewsApiService newsApiService;

    private final NewsService newsService;

    public void fetchProcessAndSaveNews(){
        // 1. Make a request to the external API to fetch news data
        // 2. Process the data (scrape additional content, summarize, etc.)
        // 3. Save the processed data to your database

        String category = "technology";
        NewsApiResponseDTO newsApiResponseDTO = fetchNews(category);
        Set<News> news = newsApiResponseDTO.getArticles().stream()
                .filter(newsService::checkIfArticleExists)
                .map(this::processArticle)
                .collect(Collectors.toSet());
    }

    public NewsApiResponseDTO fetchNews(String category){
        NewsApiResponseDTO newsApiResponseDto = newsApiService.fetchTopHeadlinesByCategory(category);
        log.info(newsApiResponseDto.toString());
        return newsApiResponseDto;
    }

    private News processArticle(NewsApiResponseDTO.ArticlesDTO article){
        String prompt = createPrompt(article.getTitle(), article.getContent(), article.getDescription());
        log.info(prompt);
        return null;
    }

    private String createPrompt(String title, String content, String description){
        return String.format("Based on the following title: %s, content: %s, and description: %s, " +
                "create a short article:", title, content, description);
    }

}
