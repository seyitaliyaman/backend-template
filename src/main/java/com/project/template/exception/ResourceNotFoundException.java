package com.project.template.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResourceNotFoundException extends DomainException{

    public ResourceNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public ResourceNotFoundException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode);
    }

    public ResourceNotFoundException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }

    public ResourceNotFoundException(Throwable cause, String domain) {
        super(cause, domain);
    }

    public ResourceNotFoundException(Throwable cause, ErrorCode errorCode, String domain) {
        super(cause, errorCode, domain);
    }
}
