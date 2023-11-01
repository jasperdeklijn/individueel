package com.jasper.pigrakker.repository;

import com.jasper.pigrakker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
    Optional<User> findByNameOrEmail(String username, String email);

}
