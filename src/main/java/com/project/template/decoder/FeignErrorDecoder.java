package com.project.template.decoder;

import feign.Response;
import feign.codec.ErrorDecoder;

public interface FeignErrorDecoder extends ErrorDecoder {

    Exception decode(String methodKey, Response response);
}
