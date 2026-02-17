package com.evhorus.java_spring_course.product.infrastructure.database.mapper;

import com.evhorus.java_spring_course.product.domain.entity.Product;
import com.evhorus.java_spring_course.product.infrastructure.database.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductEntityMapper {

    ProductEntity mapToProductEntity(Product product);

    Product mapToProduct(ProductEntity productEntity);
}
