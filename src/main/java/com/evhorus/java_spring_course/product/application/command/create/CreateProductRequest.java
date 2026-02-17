package com.evhorus.java_spring_course.product.application.command.create;


import com.evhorus.java_spring_course.common.mediator.Request;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateProductRequest implements Request<Void> {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private MultipartFile file;

}
