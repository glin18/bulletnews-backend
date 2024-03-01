package com.bulletnews.bulletnewsbackend.news.dto;

import lombok.Data;

@Data
public class NewsResponse {

    private Long id;
    private String title;
    private String description;
    private String summary;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;
    private String sourceName;
    private String categoryName;

}
