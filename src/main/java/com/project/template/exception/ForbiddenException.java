package com.project.template.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ForbiddenException extends DomainException{

    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForbiddenException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public ForbiddenException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode);
    }

    public ForbiddenException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }

    public ForbiddenException(Throwable cause, String domain) {
        super(cause, domain);
    }

    public ForbiddenException(Throwable cause, ErrorCode errorCode, String domain) {
        super(cause, errorCode, domain);
    }
}
