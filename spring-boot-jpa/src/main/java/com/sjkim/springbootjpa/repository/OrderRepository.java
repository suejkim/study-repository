package com.sjkim.springbootjpa.repository;

import com.sjkim.springbootjpa.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
