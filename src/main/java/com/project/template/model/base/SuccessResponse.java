package com.project.template.model.base;

import com.project.template.model.error.ErrorResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SuccessResponse<T> implements Response<T>{

    private final T body;

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public T getBody() {
        return this.body;
    }

    @Override
    public ErrorResponse getError() {
        return null;
    }
}
