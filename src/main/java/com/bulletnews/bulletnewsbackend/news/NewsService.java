package com.bulletnews.bulletnewsbackend.news;

import com.bulletnews.bulletnewsbackend.newsapi.NewsApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public boolean checkIfArticleExists(NewsApiResponse.ArticlesDTO article) {
        return newsRepository.existsByUrlOrTitleOrPublishedAt(article.getUrl(), article.getTitle(),
                article.getPublishedAt());
    }

    public News buildAndSaveNewsFromArticleAndSummary(NewsApiResponse.ArticlesDTO article, String summary,
                                                      News.Category category) {
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

    public List<News> findAll(){
        return newsRepository.findAll();
    }

}
