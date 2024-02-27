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
    public NewsApiResponse getTopHeadlines(@RequestParam() News.Category category) {
        return newsProcessingService.fetchTopHeadlinesByCategory(category);
    }

    @PostMapping("/admin/news/fetch-save")
    public Set<News> fetchAndSaveNews(@RequestParam() News.Category category){
        return newsProcessingService.fetchProcessAndSaveNews(category);
    }

}
