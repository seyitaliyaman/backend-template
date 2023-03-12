package com.project.template.model.base;

import com.project.template.model.error.ErrorResponse;
import com.project.template.model.error.FailureResponse;

public class Responses {

    public static <T> Response<T> ok(){
        return new SuccessResponse<>(null);
    }

    public static <T> Response<T> ok(final T body){
        return new SuccessResponse<>(body);
    }

    public static <T> Response<T> error(final ErrorResponse errorResponse){
        return new FailureResponse<>(errorResponse);
    }
}
