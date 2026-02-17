package com.evhorus.java_spring_course.product.infrastructure.api.mapper;


import com.evhorus.java_spring_course.product.application.command.create.CreateProductRequest;
import com.evhorus.java_spring_course.product.application.command.update.UpdateProductRequest;
import com.evhorus.java_spring_course.product.domain.entity.Product;
import com.evhorus.java_spring_course.product.infrastructure.api.dto.CreateProductDto;
import com.evhorus.java_spring_course.product.infrastructure.api.dto.ProductDto;
import com.evhorus.java_spring_course.product.infrastructure.api.dto.UpdateProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {

    CreateProductRequest mapToCreateProductRequest(CreateProductDto createProductDto);

    UpdateProductRequest mapToUpdateProductRequest(UpdateProductDto updateProductDto);

    ProductDto mapToProductDto(Product product);
}
