package com.bulletnews.bulletnewsbackend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    NOT_FOUND("API404", "The requested resource was not found."),
    VALIDATION_ERROR("API400", "Validation failed for the request."),
    INTERNAL_SERVER_ERROR("API500", "Internal server error occurred."),
    AUTHENTICATION_FAILED("AUTH001", "Authentication failed."),
    PERMISSION_DENIED("AUTH002", "Permission denied.");

    private final String code;
    private final String message;

}
