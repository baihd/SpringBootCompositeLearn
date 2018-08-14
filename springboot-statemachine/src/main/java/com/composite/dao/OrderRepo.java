package com.composite.dao;

import com.composite.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Integer> {

    Order findByOrderId(Integer order);

}
