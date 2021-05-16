package com.jpa.test.bootjpaexample.repositories;

import com.jpa.test.bootjpaexample.entities.Phone;
import com.jpa.test.bootjpaexample.entities.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<Phone,Integer>, JpaSpecificationExecutor<Phone> {
}
