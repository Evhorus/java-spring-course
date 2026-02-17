package com.evhorus.java_spring_course.product.application.query.getAll;

import com.evhorus.java_spring_course.common.mediator.RequestHandler;
import com.evhorus.java_spring_course.product.domain.entity.Product;
import com.evhorus.java_spring_course.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllProductsHandler implements RequestHandler<GetAllProductsRequest, GetAllProductsResponse> {


    private final ProductRepository productRepository;

    @Override
    public GetAllProductsResponse handle(GetAllProductsRequest request) {

        List<Product> products = productRepository.findAll();

        return new GetAllProductsResponse(products);
    }

    @Override
    public Class<GetAllProductsRequest> getRequestType() {
        return GetAllProductsRequest.class;
    }
}
