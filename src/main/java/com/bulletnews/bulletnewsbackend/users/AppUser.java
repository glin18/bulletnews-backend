package com.bulletnews.bulletnewsbackend.users;

import com.bulletnews.bulletnewsbackend.comments.Comment;
import com.bulletnews.bulletnewsbackend.news.News;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uuid;

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

    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

}
