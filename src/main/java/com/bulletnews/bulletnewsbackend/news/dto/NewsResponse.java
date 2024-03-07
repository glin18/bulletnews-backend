package com.bulletnews.bulletnewsbackend.news.dto;

import lombok.Data;

import java.util.List;

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
    private List<String> usersWhoLiked;
    private List<String> usersWhoSaved;

}
