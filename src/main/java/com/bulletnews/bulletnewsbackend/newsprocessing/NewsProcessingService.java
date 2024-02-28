package com.bulletnews.bulletnewsbackend.newsprocessing;

import com.bulletnews.bulletnewsbackend.news.News;
import com.bulletnews.bulletnewsbackend.news.NewsService;
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

    public void fetchProcessAndSaveNewsForEachCategory(){
        for (News.Category category : News.Category.values()){
            fetchProcessAndSaveNews(category);
        }
    }

    public Set<News> fetchProcessAndSaveNews(News.Category category) {
        NewsApiResponse newsApiResponse = fetchTopHeadlinesByCategory(category);
        return newsApiResponse.getArticles().stream()
                .filter(article -> !newsService.checkIfArticleExists(article))
                .map(article -> processArticle(article, category))
                .collect(Collectors.toSet());
    }

    public NewsApiResponse fetchTopHeadlinesByCategory(News.Category category) {
        return newsApiService.fetchTopHeadlinesByCategory(category.name().toLowerCase());
    }

    private News processArticle(NewsApiResponse.ArticlesDTO article, News.Category category) {
        String summary = openAiService.generateShortArticle(article.getTitle(), article.getContent(),
                article.getDescription());
        return newsService.buildAndSaveNewsFromArticleAndSummary(article, summary, category);
    }

}
