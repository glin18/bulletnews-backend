package com.bulletnews.bulletnewsbackend.newsprocessing;

import com.bulletnews.bulletnewsbackend.news.News;
import com.bulletnews.bulletnewsbackend.newsapi.NewsApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NewsProcessingController {

    private final NewsProcessingService newsProcessingService;

    @GetMapping("/top-headlines")
    public NewsApiResponse getTopHeadlines(@RequestParam() String category) {
        return newsProcessingService.fetchTopHeadlinesByCategory(category);
    }

    @PostMapping("/news/fetch-save")
    public List<News> fetchAndSaveNews(){
        return newsProcessingService.fetchProcessAndSaveNewsForEachCategory();
    }

}
