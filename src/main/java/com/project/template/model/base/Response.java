package com.project.template.model.base;

import com.project.template.model.error.ErrorResponse;

public interface Response<T> {
    boolean isSuccess();

    T getBody();

    ErrorResponse getError();
}
