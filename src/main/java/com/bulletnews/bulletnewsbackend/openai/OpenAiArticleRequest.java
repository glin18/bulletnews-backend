package com.bulletnews.bulletnewsbackend.openai;

import lombok.Data;

@Data
public class OpenAiArticleRequest {
    private String title;
    private String content;
    private String description;
}
