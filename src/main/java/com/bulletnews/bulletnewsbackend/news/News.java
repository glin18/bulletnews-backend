package com.bulletnews.bulletnewsbackend.news;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String title;

    private String description;

    private String summary;

    @Column(length = 1000)
    private String url;

    @Column(length = 1000)
    private String urlToImage;

    private String publishedAt;

    private String content;

    private String sourceName;

}
