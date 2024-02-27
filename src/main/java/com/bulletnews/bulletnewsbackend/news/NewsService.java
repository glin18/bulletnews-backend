package com.bulletnews.bulletnewsbackend.news;

import com.bulletnews.bulletnewsbackend.newsapi.NewsApiResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NewsService {

    private final NewsRepository  newsRepository;

    public boolean checkIfArticleExists(NewsApiResponseDTO.ArticlesDTO article){
        return newsRepository.existsByUrlOrTitleOrPublishedAt(article.getUrl(), article.getTitle(),
                article.getPublishedAt());
    }

}
