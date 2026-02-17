package com.evhorus.java_spring_course.product.domain.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("The Product with ID: " + id + " was not found.");
    }
}
