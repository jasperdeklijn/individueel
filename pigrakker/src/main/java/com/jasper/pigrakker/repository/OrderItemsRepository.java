package com.jasper.pigrakker.repository;

import com.jasper.pigrakker.model.Order;
import com.jasper.pigrakker.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {
    Optional<OrderItem> findById(long id);


}
