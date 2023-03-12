package com.project.template.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.template.model.error.ErrorResponse;
import feign.Response;
import feign.Util;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class DomainException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private ErrorCode errorCode;
    private String domain;
    private ErrorResponse errorResponse;

    public DomainException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public DomainException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public DomainException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public DomainException(Throwable cause, String domain) {
        super(cause);
        this.domain = domain;
    }

    public DomainException(Throwable cause, ErrorCode errorCode, String domain) {
        super(cause);
        this.errorCode = errorCode;
        this.domain = domain;
    }

    public DomainException errorResponse(Response.Body body) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ErrorResponse errorResponse = (ErrorResponse) mapper.readValue(Util.toString(body.asReader()), ErrorResponse.class);
            this.setErrorResponse(errorResponse);
        } catch (Exception e) {
            throw new DomainException();
        }
        return this;
    }
}
