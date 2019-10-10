package com.lambdaschool.javaorders.controllers;

import com.lambdaschool.javaorders.models.Customers;
import com.lambdaschool.javaorders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //localhost:2019/
    // GET request - /customers/order - returns all customers orders
    @GetMapping(value = "/order", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomers() {

        List<Customers> myList = customerService.findAll();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    // POST /customer/new - Adds a new customer including any new orders
    @PostMapping(value="/new",consumes = {"application/json"})
    public ResponseEntity<?> addNewCustomer(@Valid @RequestBody Customers newCustomer)
    {
        newCustomer=customerService.save(newCustomer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // PUT request /customer/update/{custcode} - updates the customer based off of custcode
    @PutMapping(value = "/update/{custcode}",consumes = {"application/json"})
    public ResponseEntity<?> updateCustomer(@RequestBody Customers customer,@PathVariable long custcode)
    {
        customerService.update(customer,custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE /customer/delete/{custcode} - Deletes the customer based off of custcode
    // this should also delete the orders of that customer
    @DeleteMapping("/delete/{custcode}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long custcode)
    {
        customerService.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
