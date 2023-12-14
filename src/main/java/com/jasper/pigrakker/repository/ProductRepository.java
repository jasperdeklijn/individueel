package com.jasper.pigrakker.repository;

import com.jasper.pigrakker.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductname(String username);

    @Query(value = "SELECT * FROM products WHERE totalKg > 0 ORDER BY (sold / totalKg) ASC, id ASC LIMIT 1", nativeQuery = true)
    Optional<Product> findFirstProductWithLowestPercentageSold();


}
