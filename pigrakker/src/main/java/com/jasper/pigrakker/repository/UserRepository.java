package com.jasper.pigrakker.repository;

import com.jasper.pigrakker.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByFirstName(String string);
}
