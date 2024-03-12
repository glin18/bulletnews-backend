package com.bulletnews.bulletnewsbackend.comments;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Data
public class CommentResponse {
    private Long id;
    private String content;
    private Instant createdTime;
    private Long newsId;
    private Long userId;
    private String uuid;
}
