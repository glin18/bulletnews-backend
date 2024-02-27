package com.bulletnews.bulletnewsbackend.news;

import com.bulletnews.bulletnewsbackend.newsapi.NewsApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NewsController {

    private final NewsProcessingService newsProcessingService;

    private final NewsService newsService;

    @GetMapping("/news")
    public List<News> findAll(){
        return newsService.findAll();
    }

    @GetMapping("/top-headlines")
    public NewsApiResponse getTopHeadlines(@RequestParam(required = false) String category) {
        return newsProcessingService.fetchNews(category);
    }

    @PostMapping("/admin/news/fetch-save")
    public Set<News> fetchAndSaveNews(){
        return newsProcessingService.fetchProcessAndSaveNews();
    }

}
