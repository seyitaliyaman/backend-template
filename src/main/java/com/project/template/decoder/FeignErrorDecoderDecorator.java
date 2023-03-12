package com.project.template.decoder;

import feign.Response;

public abstract class FeignErrorDecoderDecorator implements FeignErrorDecoder{

    protected FeignErrorDecoder feignErrorDecoder;

    protected FeignErrorDecoderDecorator(FeignErrorDecoder feignErrorDecoder){
        this.feignErrorDecoder = feignErrorDecoder;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        return feignErrorDecoder.decode(methodKey,response);
    }
}
