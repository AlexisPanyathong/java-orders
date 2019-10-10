package com.lambdaschool.javaorders.repos;

import org.springframework.data.repository.CrudRepository;
import com.lambdaschool.javaorders.models.Customers;

public interface CustomerRepo extends CrudRepository<Customers, Long> {
}
