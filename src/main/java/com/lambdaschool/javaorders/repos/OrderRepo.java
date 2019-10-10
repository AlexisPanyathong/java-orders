package com.lambdaschool.javaorders.repos;

import org.springframework.data.repository.CrudRepository;
import com.lambdaschool.javaorders.models.Orders;

public interface OrderRepo extends CrudRepository<Orders, Long> {
}
