package com.bulletnews.bulletnewsbackend.users.dto;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class UserResponse {
    private Long id;
    private String uuid;
    private String email;
    private Instant lastLoginTime;
    private List<Long> likedNews;
}
