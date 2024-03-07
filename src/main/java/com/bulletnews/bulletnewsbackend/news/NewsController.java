package com.bulletnews.bulletnewsbackend.news;

import com.bulletnews.bulletnewsbackend.news.dto.NewsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/news")
    public List<NewsResponse> findAll(){
        return newsService.findAll();
    }

    @GetMapping("/news/{id}")
    public NewsResponse findById(@PathVariable Long id){
        return newsService.findById(id);
    }

    @GetMapping("/category/{id}/news")
    public List<NewsResponse> findAllByCategoryId(@PathVariable Long id){
        return newsService.findAllByCategoryId(id);
    }

    @PostMapping("/news/{newsId}/like")
    public ResponseEntity<Void> likeArticle(@PathVariable Long newsId, @AuthenticationPrincipal Jwt jwt){
        newsService.likeNews(newsId, jwt.getSubject());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/news/{newsId}/save")
    public ResponseEntity<Void> saveArticle(@PathVariable Long newsId, @AuthenticationPrincipal Jwt jwt){
        newsService.saveNews(newsId, jwt.getSubject());
        return ResponseEntity.ok().build();
    }

}
