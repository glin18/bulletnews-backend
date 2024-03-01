package com.bulletnews.bulletnewsbackend.newsprocessing;

import com.bulletnews.bulletnewsbackend.category.Category;
import com.bulletnews.bulletnewsbackend.category.CategoryService;
import com.bulletnews.bulletnewsbackend.news.News;
import com.bulletnews.bulletnewsbackend.news.NewsService;
import com.bulletnews.bulletnewsbackend.newsapi.NewsApiResponse;
import com.bulletnews.bulletnewsbackend.newsapi.NewsApiService;
import com.bulletnews.bulletnewsbackend.openai.OpenAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class NewsProcessingService {

    private final NewsApiService newsApiService;

    private final NewsService newsService;

    private final OpenAiService openAiService;

    private final CategoryService categoryService;

    public List<News> fetchProcessAndSaveNewsForEachCategory(){
        List<Category> categories = categoryService.findAll();
        return categories.stream().flatMap(category -> fetchProcessAndSaveNews(category).stream())
                .collect(Collectors.toList());
    }

    public List<News> fetchProcessAndSaveNews(Category category) {
        NewsApiResponse newsApiResponse = fetchTopHeadlinesByCategory(category.getSearchTerm());
        return newsApiResponse.getArticles().stream()
            .filter(article -> !newsService.checkIfArticleExists(article))
            .map(article -> processArticle(article, category)).collect(Collectors.toList());
    }

    public NewsApiResponse fetchTopHeadlinesByCategory(String searchTerm) {
        return newsApiService.fetchTopHeadlinesByCategory(searchTerm);
    }

    private News processArticle(NewsApiResponse.ArticlesDTO article, Category category) {
        String summary = openAiService.generateShortArticle(article.getTitle(), article.getContent(),
                article.getDescription());
        return newsService.buildAndSaveNewsFromArticleAndSummary(article, summary, category);
    }

}
