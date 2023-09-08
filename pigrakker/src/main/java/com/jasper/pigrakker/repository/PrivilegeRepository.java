package com.jasper.pigrakker.repository;

import com.jasper.pigrakker.model.Privilege;
import org.springframework.data.repository.CrudRepository;


public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
    Privilege findByName(String name);
}
