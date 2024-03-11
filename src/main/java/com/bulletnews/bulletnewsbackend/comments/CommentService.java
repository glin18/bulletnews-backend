package com.bulletnews.bulletnewsbackend.comments;

import com.bulletnews.bulletnewsbackend.news.News;
import com.bulletnews.bulletnewsbackend.news.NewsService;
import com.bulletnews.bulletnewsbackend.users.AppUser;
import com.bulletnews.bulletnewsbackend.users.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final NewsService newsService;
    private final AppUserService appUserService;

    public List<CommentResponse> findAll() {
        return commentRepository.findAll().stream().map(this::mapCommentToResponse).collect(Collectors.toList());
    }

    public CommentResponse createComment(CreateCommentRequest request, Long newsId, String uuid) {
        News news = newsService.findById(newsId);
        AppUser user = appUserService.findByUuid(uuid);
        Comment comment = Comment.builder()
                .content(request.getContent())
                .createdTime(Instant.now())
                .news(news)
                .user(user)
                .build();
        return mapCommentToResponse(commentRepository.save(comment));
    }

    private CommentResponse mapCommentToResponse(Comment comment) {
        CommentResponse response = new CommentResponse();
        BeanUtils.copyProperties(comment, response);
        Optional.ofNullable(comment.getNews())
                .map(News::getId)
                .ifPresent(response::setNewsId);
        Optional.ofNullable(comment.getUser())
                .map(AppUser::getId)
                .ifPresent(response::setUserId);
        return response;
    }

    public List<CommentResponse> findAllByNewsId(Long id) {
        List<Comment> comments = commentRepository.findAllByNewsId(id);
        return comments.stream()
                .map(this::mapCommentToResponse)
                .collect(Collectors.toList());
    }
}
