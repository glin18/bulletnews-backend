package com.bulletnews.bulletnewsbackend.comments;

import com.bulletnews.bulletnewsbackend.news.News;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String content;

    private Instant createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private News news;

}
