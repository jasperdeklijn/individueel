package com.Jasper.pigrakker.repository;

import com.Jasper.pigrakker.model.User;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.CrudRepository;

@EnableMongoRepositories
public interface UserRepository extends CrudRepository<User, Long> {
    User findByFirstName(String string);
}
