package com.evhorus.java_spring_course.product.application.scheduling;

import com.evhorus.java_spring_course.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FixProductsPriceSchedule {

    private final ProductRepository productRepository;

    @Scheduled(fixedRate = 6000)
    public void fixProductsPrice() {
        log.info("Fixing products price schedule");

        productRepository.findAll().forEach(product -> {
            product.setPrice(product.getPrice() * product.getPrice());
            productRepository.save(product);
        });

        log.info("Finished products price schedule");
    }
}
