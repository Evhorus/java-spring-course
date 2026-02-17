package com.evhorus.java_spring_course.product.infrastructure.database;

import com.evhorus.java_spring_course.product.domain.entity.Product;
import com.evhorus.java_spring_course.product.domain.port.ProductRepository;
import com.evhorus.java_spring_course.product.infrastructure.database.entity.ProductEntity;
import com.evhorus.java_spring_course.product.infrastructure.database.mapper.ProductEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductRepositoryImpl implements ProductRepository {

    private final List<ProductEntity> products = new ArrayList<>();

    private final ProductEntityMapper productEntityMapper;


    @Override
    public void save(Product product) {
        ProductEntity productEntity = productEntityMapper.mapToProductEntity(product);
        products.removeIf(p -> p.getId().equals(productEntity.getId()));
        products.add(productEntity);
    }


    @Cacheable(value = "products", key = "#id")
    @Override
    public Optional<Product> findById(Long id) {

        log.info("Finding product by id {}", id);
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .map(productEntityMapper::mapToProduct);
    }

    @Override
    public List<Product> findAll() {
        return products.stream().map(productEntityMapper::mapToProduct).toList();
    }

    @Override
    public void deleteById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}
