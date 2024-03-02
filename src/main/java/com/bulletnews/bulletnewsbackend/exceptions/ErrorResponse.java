package com.bulletnews.bulletnewsbackend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private String code;
    private String message;
    private String detail;
}
