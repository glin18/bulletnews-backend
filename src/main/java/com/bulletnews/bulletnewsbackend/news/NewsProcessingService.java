package com.bulletnews.bulletnewsbackend.news;

import com.bulletnews.bulletnewsbackend.newsapi.NewsApiResponse;
import com.bulletnews.bulletnewsbackend.newsapi.NewsApiService;
import com.bulletnews.bulletnewsbackend.openai.OpenAiService;
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

    private final OpenAiService openAiService;

    public void fetchProcessAndSaveNews(){
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
        String summary = openAiService.generateShortArticle(article.getTitle(), article.getContent(),
                article.getDescription());
        return newsService.buildAndSaveNewsFromArticleAndSummary(article, summary);
    }

}
