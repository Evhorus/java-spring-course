package com.evhorus.java_spring_course.product.application.query.getAll;

import com.evhorus.java_spring_course.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class GetAllProductsResponse {
    private List<Product> products;
}
