package com.bulletnews.bulletnewsbackend.openai;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/openai")
public class OpenAiController {

    private final OpenAiService openAiService;

    @PostMapping("/completion")
    public String getOpenAiCompletion(@RequestBody OpenAiArticleRequest request){
        return openAiService.generateShortArticle(request.getTitle(), request.getContent(), request.getDescription());
    }
}
