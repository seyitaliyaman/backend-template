package com.project.template.exception;

import com.project.template.converter.ExceptionToErrorResponseConverter;
import com.project.template.model.error.ErrorResponse;
import com.project.template.model.error.FieldBasedErrorInfo;
import com.project.template.model.base.Response;
import com.project.template.model.base.Responses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionHandlerTemplate {

    @Value("${spring.application.name}")
    private String domainName;
    @Value("${api.exception.general.code}")
    private int generalErrorCode;
    @Value("${api.exception.general.message}")
    private String generalErrorMessage;

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    private Response<ErrorResponse> handleUnauthorizedResponse(UnauthorizedException unauthorizedException) {
        return getErrorResponse(unauthorizedException);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private Response<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        return getErrorResponse(resourceNotFoundException);
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    private Response<ErrorResponse> handleForbiddenException(ForbiddenException forbiddenException){
        return getErrorResponse(forbiddenException);
    }

    @ExceptionHandler(DomainException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private Response<ErrorResponse> handleDomainException(DomainException domainException){
        return getErrorResponse(domainException);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private Response<ErrorResponse> handleException(Exception e){
        log.error("Exception: ",e);
        return Responses.error(getGeneralErrorResponse());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private Response<ErrorResponse> processInternalServerError(Throwable throwable) {
        log.error("Throwable: ", throwable);
        return Responses.error(ExceptionToErrorResponseConverter.convert(
                throwable,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                Optional.of(domainName).orElse("UNKNOWN")
        ));
    }

    private Response<ErrorResponse> getErrorResponse(DomainException domainException) {
        return Responses.error(Optional.ofNullable(domainException.getErrorResponse()).orElseGet(()-> this.convert(domainException)));
    }

    private ErrorResponse convert(DomainException domainException) {

        try {

            final var errorCode = Optional.ofNullable(domainException.getErrorCode());
            if (errorCode.isPresent()) {
                final var errorResponse = ErrorResponse.builder()
                        .code(errorCode.map(ErrorCode::getCode).orElse(HttpStatus.BAD_REQUEST.value()))
                        .message(errorCode.map(ErrorCode::getMessage).orElse(domainException.getMessage()))
                        .domain(Objects.isNull(domainException.getDomain()) ? domainName : domainException.getDomain())
                        .build();

                if (errorCode.get() instanceof FieldBasedErrorCode) {
                    final var fieldBasedErrorCode = (FieldBasedErrorCode) errorCode.get();
                    final var fieldBasedErrorInfo = FieldBasedErrorInfo.builder()
                            .location(fieldBasedErrorCode.getLocation())
                            .locationType(fieldBasedErrorCode.getLocationType())
                            .build();
                    errorResponse.setExtras(fieldBasedErrorInfo);
                }
                return errorResponse;
            } else {
                log.error("Cannot get ErrorCode! Return general error response.", domainException);
                return getGeneralErrorResponse();
            }
        } catch (Exception e) {
            log.error("Cannot parse domain exception. Return general error.", domainException);
            return getGeneralErrorResponse();
        }
    }

    private ErrorResponse getGeneralErrorResponse() {
        final var errorResponse = ErrorResponse.builder()
                .domain(domainName)
                .code(generalErrorCode)
                .message(generalErrorMessage)
                .build();
        log.error(errorResponse.toString());
        return errorResponse;
    }
}
