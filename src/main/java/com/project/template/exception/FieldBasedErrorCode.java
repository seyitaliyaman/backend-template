package com.project.template.exception;

public interface FieldBasedErrorCode extends ErrorCode{

    String getLocation();

    default String getLocationType() {
        return "field";
    }


}
