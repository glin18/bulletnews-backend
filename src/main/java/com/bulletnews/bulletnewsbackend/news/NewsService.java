package com.bulletnews.bulletnewsbackend.news;

import com.bulletnews.bulletnewsbackend.category.Category;
import com.bulletnews.bulletnewsbackend.exceptions.custom.ResourceNotFoundException;
import com.bulletnews.bulletnewsbackend.news.dto.NewsResponse;
import com.bulletnews.bulletnewsbackend.newsapi.NewsApiResponse;
import com.bulletnews.bulletnewsbackend.users.AppUser;
import com.bulletnews.bulletnewsbackend.users.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NewsService {

    private final NewsRepository newsRepository;
    private final AppUserService appUserService;

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

    public List<NewsResponse> findAll() {
        List<News> newsList = newsRepository.findAll();
        return newsList.stream().map(this::newsToNewsResponseMapper).collect(Collectors.toList());
    }

    public List<NewsResponse> findAllByCategoryId(Long categoryId) {
        List<News> newsList = newsRepository.findAllByCategory_Id(categoryId);
        return newsList.stream().map(this::newsToNewsResponseMapper).collect(Collectors.toList());
    }

    private NewsResponse newsToNewsResponseMapper(News news) {
        NewsResponse newsResponse = new NewsResponse();
        BeanUtils.copyProperties(news, newsResponse);
        String categoryName = Optional.ofNullable(news.getCategory())
                .map(Category::getName)
                .orElse("No Category");
        newsResponse.setCategoryName(categoryName);
        newsResponse.setUsersWhoLiked(news.getUsersWhoLiked().stream().map(AppUser::getUuid)
                .collect(Collectors.toList()));
        return newsResponse;
    }

    @Transactional
    public void likeNews(Long newsId, String uuid) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new ResourceNotFoundException("News with ID of " + newsId + " not found"));
        AppUser user = appUserService.likeNews(news, uuid);
        if (!news.getUsersWhoLiked().contains(user)) {
            news.getUsersWhoLiked().add(user);
        } else {
            news.getUsersWhoLiked().remove(user);
        }
        newsRepository.save(news);
    }
}
