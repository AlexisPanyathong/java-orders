package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Customers;
import java.util.List;

public class CustomerService {

    // find all
    List<Customers> findAll();

    // dont need find by id - was causing errors

    // save
    Customers save(Customers customers);

    // update by id
    Customers update(Customers customers, long id);

    // delete
    void delete(long id);

}
