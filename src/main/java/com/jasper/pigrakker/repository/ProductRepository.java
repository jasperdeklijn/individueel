package com.jasper.pigrakker.repository;

import com.jasper.pigrakker.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductname(String username);
    Optional<Product> findById(long id);


}
