package com.bulletnews.bulletnewsbackend.newsprocessing;

import com.bulletnews.bulletnewsbackend.news.News;
import com.bulletnews.bulletnewsbackend.newsapi.NewsApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NewsProcessingController {

    private final NewsProcessingService newsProcessingService;

    @GetMapping("/top-headlines")
    public NewsApiResponse getTopHeadlines(@RequestParam() News.Category category) {
        return newsProcessingService.fetchTopHeadlinesByCategory(category);
    }

    @PostMapping("/admin/news/fetch-save")
    public Set<News> fetchAndSaveNews(@RequestParam() News.Category category){
        return newsProcessingService.fetchProcessAndSaveNews(category);
    }

}
