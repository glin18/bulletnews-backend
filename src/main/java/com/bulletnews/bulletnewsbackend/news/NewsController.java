package com.bulletnews.bulletnewsbackend.news;

import com.bulletnews.bulletnewsbackend.newsapi.NewsApiResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NewsController {

    private final NewsProcessingService newsProcessingService;

    @GetMapping("/top-headlines")
    public NewsApiResponseDTO getTopHeadlines(@RequestParam(required = false) String category) {
        return newsProcessingService.fetchNews(category);
    }

}
