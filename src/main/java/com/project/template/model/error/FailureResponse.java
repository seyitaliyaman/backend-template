package com.project.template.model.error;

import com.project.template.model.base.Response;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FailureResponse<T> implements Response<T> {

    private final ErrorResponse errorResponse;

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public T getBody() {
        return null;
    }

    @Override
    public ErrorResponse getError() {
        return this.errorResponse;
    }
}
