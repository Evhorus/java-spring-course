package com.evhorus.java_spring_course.product.application.command.create;


import com.evhorus.java_spring_course.common.mediator.RequestHandler;
import com.evhorus.java_spring_course.common.util.FileUtils;
import com.evhorus.java_spring_course.product.domain.entity.Product;
import com.evhorus.java_spring_course.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProductHandler implements RequestHandler<CreateProductRequest, Void> {


    private final ProductRepository productRepository;

    private final FileUtils fileUtils;


    @Override
    public Void handle(CreateProductRequest request) {

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
    public Class<CreateProductRequest> getRequestType() {
        return CreateProductRequest.class;
    }
}
