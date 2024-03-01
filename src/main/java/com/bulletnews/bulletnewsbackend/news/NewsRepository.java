package com.bulletnews.bulletnewsbackend.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    Optional<News> findByUrlOrTitleOrPublishedAt(String url, String title, String publishedAt);
    boolean existsByUrlOrTitleOrPublishedAt(String url, String title, String publishedAt);
    List<News> findAllByCategory_Id(Long id);
}
