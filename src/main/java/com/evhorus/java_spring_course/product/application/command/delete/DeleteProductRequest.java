package com.evhorus.java_spring_course.product.application.command.delete;

import com.evhorus.java_spring_course.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteProductRequest implements Request<Void> {
    private Long id;
}
