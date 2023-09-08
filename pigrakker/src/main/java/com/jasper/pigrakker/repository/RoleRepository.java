package com.jasper.pigrakker.repository;

import com.jasper.pigrakker.model.Role;
import org.springframework.data.repository.CrudRepository;


public interface RoleRepository extends CrudRepository<Role, Long> {
   Role findByName(String string);
}
