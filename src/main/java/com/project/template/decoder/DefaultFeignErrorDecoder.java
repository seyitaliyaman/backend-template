package com.project.template.decoder;

import com.project.template.exception.*;
import feign.Response;
import org.springframework.http.HttpStatus;

import java.util.Objects;

public class DefaultFeignErrorDecoder implements FeignErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (Objects.equals(response.status(), HttpStatus.BAD_REQUEST.value()))
            return new BadRequestException().errorResponse(response.body());
        if (Objects.equals(response.status(), HttpStatus.UNAUTHORIZED.value()))
            return new UnauthorizedException().errorResponse(response.body());
        if (Objects.equals(response.status(), HttpStatus.FORBIDDEN.value()))
            return new ForbiddenException().errorResponse(response.body());
        if (Objects.equals(response.status(), HttpStatus.NOT_FOUND.value()))
            return new ResourceNotFoundException().errorResponse(response.body());
        return new DomainException().errorResponse(response.body());
    }
}
