package com.bulletnews.bulletnewsbackend.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    boolean existsByUrlOrTitleOrPublishedAt(String url, String title, String publishedAt);
    List<News> findAllByCategory_Id(Long id);
}
