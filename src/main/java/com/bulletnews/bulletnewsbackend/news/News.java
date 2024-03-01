package com.bulletnews.bulletnewsbackend.news;

import com.bulletnews.bulletnewsbackend.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String title;

    @Column(length = 5000)
    private String description;

    @Column(length = 5000)
    private String summary;

    @Column(length = 1000)
    private String url;

    @Column(length = 1000)
    private String urlToImage;

    private String publishedAt;

    @Column(length = 5000)
    private String content;

    private String sourceName;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

}
