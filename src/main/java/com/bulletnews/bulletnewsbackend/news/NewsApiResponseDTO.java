package com.bulletnews.bulletnewsbackend.news;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class NewsApiResponseDTO {

    @JsonProperty("status")
    private String status;
    @JsonProperty("totalResults")
    private Integer totalResults;
    @JsonProperty("articles")
    private List<ArticlesDTO> articles;

    @NoArgsConstructor
    @Data
    public static class ArticlesDTO {
        @JsonProperty("source")
        private SourceDTO source;
        @JsonProperty("author")
        private Object author;
        @JsonProperty("title")
        private String title;
        @JsonProperty("description")
        private String description;
        @JsonProperty("url")
        private String url;
        @JsonProperty("urlToImage")
        private String urlToImage;
        @JsonProperty("publishedAt")
        private String publishedAt;
        @JsonProperty("content")
        private String content;

        @NoArgsConstructor
        @Data
        public static class SourceDTO {
            @JsonProperty("id")
            private Object id;
            @JsonProperty("name")
            private String name;
        }
    }
}
