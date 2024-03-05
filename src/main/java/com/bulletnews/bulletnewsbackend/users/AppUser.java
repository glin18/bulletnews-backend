package com.bulletnews.bulletnewsbackend.users;

import com.bulletnews.bulletnewsbackend.news.News;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class AppUser {

    @Id
    private String id;

    @Column(nullable = false)
    private String email;

    private Instant lastLoginTime;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "app_user_liked_news",
            joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "news_id")
    )
    private Set<News> likedNews = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "app_user_saved_news",
            joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "news_id")
    )
    private Set<News> savedNews = new HashSet<>();

}
