package com.bulletnews.bulletnewsbackend.news;

import com.bulletnews.bulletnewsbackend.category.Category;
import com.bulletnews.bulletnewsbackend.news.dto.NewsResponse;
import com.bulletnews.bulletnewsbackend.newsapi.NewsApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public boolean checkIfArticleExists(NewsApiResponse.ArticlesDTO article) {
        return newsRepository.existsByUrlOrTitleOrPublishedAt(article.getUrl(), article.getTitle(),
                article.getPublishedAt());
    }

    public News buildAndSaveNewsFromArticleAndSummary(NewsApiResponse.ArticlesDTO article, String summary,
                                                      Category category) {
        News news = News.builder()
                .title(article.getTitle())
                .description(article.getDescription())
                .summary(summary)
                .url(article.getUrl())
                .urlToImage(article.getUrlToImage())
                .publishedAt(article.getPublishedAt())
                .content(article.getContent())
                .sourceName(article.getSource().getName())
                .category(category)
                .build();
        return newsRepository.save(news);
    }

    public List<NewsResponse> findAll(){
        List<News> newsList = newsRepository.findAll();
        return newsList.stream().map(this::newsToNewsResponseMapper).collect(Collectors.toList());
    }

    public List<NewsResponse> findAllByCategoryId(Long categoryId) {
        List<News> newsList = newsRepository.findAllByCategory_Id(categoryId);
        return newsList.stream().map(this::newsToNewsResponseMapper).collect(Collectors.toList());
    }

    private NewsResponse newsToNewsResponseMapper(News news){
        NewsResponse newsResponse = new NewsResponse();
        BeanUtils.copyProperties(news, newsResponse);
        String categoryName = Optional.ofNullable(news.getCategory())
                .map(Category::getName)
                .orElse("No Category");
        newsResponse.setCategoryName(categoryName);
        return newsResponse;
    }

}
