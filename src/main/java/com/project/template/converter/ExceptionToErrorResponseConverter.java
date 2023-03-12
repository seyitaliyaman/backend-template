package com.project.template.converter;

import com.project.template.model.error.ErrorResponse;

import java.util.Optional;

public class ExceptionToErrorResponseConverter {


    public static ErrorResponse convert(final Throwable throwable, final String domain){
        return convert(throwable,0,domain);
    }

    public static ErrorResponse convert(final Throwable throwable, final int status, final String domain){
        return convert(throwable,status,domain,Optional.empty());
    }


    public static ErrorResponse convert(final Throwable throwable, final int status, final String domain, int errorCode){
        return convert(throwable,status,domain,Optional.of(errorCode));
    }


    private static ErrorResponse convert(final Throwable throwable, final int status, final String domain, Optional<Integer> errorCode){
        return ErrorResponse.builder()
                .code(errorCode.orElse(status))
                .message(throwable.getMessage())
                .domain(domain)
                .build();
    }
}
