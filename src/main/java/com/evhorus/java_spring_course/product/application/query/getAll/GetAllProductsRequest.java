package com.evhorus.java_spring_course.product.application.query.getAll;

import com.evhorus.java_spring_course.common.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class GetAllProductsRequest implements Request<GetAllProductsResponse> {
}
