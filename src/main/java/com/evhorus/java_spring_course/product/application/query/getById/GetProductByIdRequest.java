package com.evhorus.java_spring_course.product.application.query.getById;

import com.evhorus.java_spring_course.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class GetProductByIdRequest implements Request<GetProductByIdResponse> {
    private Long id;
}
