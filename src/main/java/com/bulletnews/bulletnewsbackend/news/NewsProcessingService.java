package com.bulletnews.bulletnewsbackend.news;

import com.bulletnews.bulletnewsbackend.newsapi.NewsApiResponse;
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
        NewsApiResponse newsApiResponse = fetchNews(category);
        Set<News> news = newsApiResponse.getArticles().stream()
                .filter(newsService::checkIfArticleExists)
                .map(this::processArticle)
                .collect(Collectors.toSet());
    }

    public NewsApiResponse fetchNews(String category){
        NewsApiResponse newsApiResponse = newsApiService.fetchTopHeadlinesByCategory(category);
        log.info(newsApiResponse.toString());
        return newsApiResponse;
    }

    private News processArticle(NewsApiResponse.ArticlesDTO article){
        return null;
    }

}
