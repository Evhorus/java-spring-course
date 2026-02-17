package com.evhorus.java_spring_course.product.application.command.update;

import com.evhorus.java_spring_course.common.mediator.RequestHandler;
import com.evhorus.java_spring_course.common.util.FileUtils;
import com.evhorus.java_spring_course.product.domain.entity.Product;
import com.evhorus.java_spring_course.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateProductHandler implements RequestHandler<UpdateProductRequest, Void> {


    private final ProductRepository productRepository;

    private final FileUtils fileUtils;


    @Override
    public Void handle(UpdateProductRequest request) {

        String uniqueFileName = fileUtils.saveProductImage(request.getFile());

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .image(uniqueFileName)
                .id(1L)
                .build();


        productRepository.save(product);

        return null;
    }

    @Override
    public Class<UpdateProductRequest> getRequestType() {
        return UpdateProductRequest.class;
    }
}
