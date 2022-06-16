package com.sjkim.springbootjpa.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    @Transactional
    void findByOrderForFetchTypeLazy() {
        var orderOptional = orderRepository.findById(1L);
        orderOptional.ifPresent(order -> {
            log.info("order {}", order);
            log.info("order status {}", order.getStatus());
            var user = order.getUser();
            log.info("user {}", user.getName());
        });
    }
}
