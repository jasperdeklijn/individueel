package com.jasper.pigrakker.repository;

import com.jasper.pigrakker.model.Product;
import com.jasper.pigrakker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductname(String username);
    Optional<Product> findById(long id);


}
