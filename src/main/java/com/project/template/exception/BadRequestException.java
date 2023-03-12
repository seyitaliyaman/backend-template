package com.project.template.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BadRequestException extends DomainException{

    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public BadRequestException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode);
    }

    public BadRequestException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }

    public BadRequestException(Throwable cause, String domain) {
        super(cause, domain);
    }

    public BadRequestException(Throwable cause, ErrorCode errorCode, String domain) {
        super(cause, errorCode, domain);
    }
}
