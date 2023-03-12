package com.project.template.decoder;

import feign.Response;

import java.util.Optional;

public abstract class CustomFeignErrorDecoder extends FeignErrorDecoderDecorator {

    protected CustomFeignErrorDecoder(FeignErrorDecoder feignErrorDecoder) {
        super(feignErrorDecoder);
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        Optional<Exception> exceptionOptional = handleCustom(methodKey,response);
        return exceptionOptional.isPresent() ? exceptionOptional.get() : feignErrorDecoder.decode(methodKey,response);
    }


    private Optional<Exception> handleCustom(String methodKey, Response response){
        Exception exception = handleCustomException(methodKey,response);
        return Optional.ofNullable(exception);
    }

    public abstract Exception handleCustomException(String methodKey, Response response);
}
