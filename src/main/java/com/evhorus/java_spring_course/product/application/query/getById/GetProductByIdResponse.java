package com.evhorus.java_spring_course.product.application.query.getById;

import com.evhorus.java_spring_course.product.domain.entity.Product;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetProductByIdResponse {
    private Product product;
}
