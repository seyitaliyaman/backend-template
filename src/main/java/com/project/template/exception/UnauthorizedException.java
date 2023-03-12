package com.project.template.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnauthorizedException extends DomainException{

    public UnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public UnauthorizedException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode);
    }

    public UnauthorizedException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }

    public UnauthorizedException(Throwable cause, String domain) {
        super(cause, domain);
    }

    public UnauthorizedException(Throwable cause, ErrorCode errorCode, String domain) {
        super(cause, errorCode, domain);
    }
}
