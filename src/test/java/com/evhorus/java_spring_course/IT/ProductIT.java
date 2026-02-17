package com.evhorus.java_spring_course.IT;


import com.evhorus.java_spring_course.product.domain.entity.Product;
import com.evhorus.java_spring_course.product.domain.port.ProductRepository;
import com.evhorus.java_spring_course.product.infrastructure.api.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@AutoConfigureMockMvc
public class ProductIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;


    @Autowired
    private ProductRepository productRepository;


    @BeforeEach
    void setUp() {
        log.info("Setting up Integration test");
        productRepository.save(
                Product.builder()
                        .id(1L)
                        .name("Product 1")
                        .description("Description 1")
                        .price(100.0)
                        .build()
        );

    }

    @AfterEach
    void tearDown() {
        log.info("Tearing down Integration test ");
        productRepository.deleteById(1L);
    }

    @Test
    public void getProductByIdExists() {
        ResponseEntity<ProductDto> response = restTemplate.getForEntity("/api/v1/products/1", ProductDto.class);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Product 1", response.getBody().getName());
    }

    @Test
    public void saveProduct() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "image.jpeg", MediaType.IMAGE_JPEG_VALUE, "image".getBytes());

        mockMvc.perform(
                multipart(HttpMethod.POST, "/api/v1/products")
                        .file(file)
                        .param("id", "2")
                        .param("name", "Product 2")
                        .param("description", "description2")
                        .param("price", "150.00")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
        ).andExpect(status().isCreated());

    }
}
