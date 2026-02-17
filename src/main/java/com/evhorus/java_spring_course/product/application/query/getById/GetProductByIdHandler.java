package com.evhorus.java_spring_course.product.application.query.getById;

import com.evhorus.java_spring_course.common.mediator.RequestHandler;
import com.evhorus.java_spring_course.product.domain.entity.Product;
import com.evhorus.java_spring_course.product.domain.exception.ProductNotFoundException;
import com.evhorus.java_spring_course.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProductByIdHandler implements RequestHandler<GetProductByIdRequest, GetProductByIdResponse> {


    private final ProductRepository productRepository;

    @Override
    public GetProductByIdResponse handle(GetProductByIdRequest request) {

        Product product = productRepository.findById(request.getId()).orElseThrow(() -> new ProductNotFoundException(request.getId()));

        return new GetProductByIdResponse(product);
    }

    @Override
    public Class<GetProductByIdRequest> getRequestType() {
        return GetProductByIdRequest.class;
    }
}
