package com.Jasper.pigrakker.repository;

import com.Jasper.pigrakker.model.Role;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.CrudRepository;

@EnableMongoRepositories
public interface RoleRepository extends CrudRepository<Role, Long> {
   Role findByName(String string);
}
