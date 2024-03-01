package com.bulletnews.bulletnewsbackend.news;

import com.bulletnews.bulletnewsbackend.news.dto.NewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/news")
    public List<NewsResponse> findAll(){
        return newsService.findAll();
    }

    @GetMapping("/category/{id}/news")
    public List<NewsResponse> findAllByCategoryId(@PathVariable Long id){
        return newsService.findAllByCategoryId(id);
    }

}
