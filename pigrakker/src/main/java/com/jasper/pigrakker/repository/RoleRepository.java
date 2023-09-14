package com.Jasper.pigrakker.repository;

import com.Jasper.pigrakker.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
   Role findByName(String string);
}
