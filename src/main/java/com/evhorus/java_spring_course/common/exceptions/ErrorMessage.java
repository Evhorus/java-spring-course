package com.evhorus.java_spring_course.common.exceptions;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ErrorMessage {
    private String message;
    private String exception;
    private String path;
    private Map<String, String> errors;

    public ErrorMessage(String message, String path, String exception) {
        this.message = message;
        this.path = path;
        this.exception = exception;
        this.errors = new HashMap<>();
    }

    public ErrorMessage(String message, String path, String exception, Map<String, String> errors) {
        this.message = message;
        this.path = path;
        this.exception = exception;
        this.errors = errors;
    }


}
