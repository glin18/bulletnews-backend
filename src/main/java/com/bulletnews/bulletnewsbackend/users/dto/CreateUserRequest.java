package com.bulletnews.bulletnewsbackend.users.dto;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String uuid;
    private String email;
}
