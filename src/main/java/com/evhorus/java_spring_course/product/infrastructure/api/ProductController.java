package com.evhorus.java_spring_course.product.infrastructure.api;

import com.evhorus.java_spring_course.common.mediator.Mediator;
import com.evhorus.java_spring_course.product.application.command.create.CreateProductRequest;
import com.evhorus.java_spring_course.product.application.command.delete.DeleteProductRequest;
import com.evhorus.java_spring_course.product.application.command.update.UpdateProductRequest;
import com.evhorus.java_spring_course.product.application.query.getAll.GetAllProductsRequest;
import com.evhorus.java_spring_course.product.application.query.getAll.GetAllProductsResponse;
import com.evhorus.java_spring_course.product.application.query.getById.GetProductByIdRequest;
import com.evhorus.java_spring_course.product.application.query.getById.GetProductByIdResponse;
import com.evhorus.java_spring_course.product.infrastructure.api.dto.CreateProductDto;
import com.evhorus.java_spring_course.product.infrastructure.api.dto.ProductDto;
import com.evhorus.java_spring_course.product.infrastructure.api.dto.UpdateProductDto;
import com.evhorus.java_spring_course.product.infrastructure.api.mapper.ProductMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Product", description = "Product API operations")
@Slf4j
public class ProductController implements ProductApi {


    private final Mediator mediator;

    private final ProductMapper productMapper;


    @Operation(summary = "Get all Products", description = "Get all products")
    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getAllProducts(@RequestParam(required = false) String pageSize) {

        log.info("Get all products");

        GetAllProductsResponse response = mediator.dispatch(new GetAllProductsRequest());

        List<ProductDto> products = response.getProducts().stream().map(productMapper::mapToProductDto).toList();

        log.info("Found {} products", products.size());

        return ResponseEntity.ok(products);
    }


    @Operation(summary = "Get product by id", description = "Get product by id")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {

        GetProductByIdResponse response = mediator.dispatch(new GetProductByIdRequest(id));


        ProductDto productDto = productMapper.mapToProductDto(response.getProduct());

        return ResponseEntity.ok(productDto);
    }

    @Operation(summary = "Save product", description = "Save product")
    @PostMapping("")
    public ResponseEntity<Void> saveProduct(@ModelAttribute @Valid CreateProductDto createProductDto) {

        CreateProductRequest createProductRequest = productMapper.mapToCreateProductRequest(createProductDto);

        mediator.dispatch(createProductRequest);

        return ResponseEntity.created(URI.create("/api/v1/products/".concat(createProductDto.getId().toString()))).build();

    }


    @Operation(summary = "Update product", description = "Update product")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@ModelAttribute @Valid UpdateProductDto updateProductDto) {

        UpdateProductRequest request = productMapper.mapToUpdateProductRequest(updateProductDto);

        mediator.dispatch(request);

        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Delete product", description = "Delete product")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        mediator.dispatchAsync(new DeleteProductRequest(id));

        return ResponseEntity.noContent().build();
    }


}
