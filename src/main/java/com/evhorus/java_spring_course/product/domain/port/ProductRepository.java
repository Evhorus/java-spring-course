package com.evhorus.java_spring_course.product.domain.port;


import com.evhorus.java_spring_course.product.domain.entity.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    void save(@RequestBody Product product);


    Optional<Product> findById(@PathVariable Long id);

    List<Product> findAll();

    void deleteById(@PathVariable Long id);
}
