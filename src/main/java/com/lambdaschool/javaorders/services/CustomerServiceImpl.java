package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Customers;
import com.lambdaschool.javaorders.models.Orders;
import com.lambdaschool.javaorders.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    // to access the orders repository
    private CustomerRepo custrepo;

    // generate override methods from orders
    // find all
    @Override
    public List<Customers> findAll()
    {
        List<Customers> rtnList = new ArrayList<>();
        custrepo.findAll().iterator().forEachRemaining(rtnList::add);
        return rtnList;
    }

    // spring framework transactional - all steps have to happen correctly otherwise nothing will work
    @Transactional
    // save
    @Override
    public Customers save(Customers customer)
    {
        Customers newCustomer = new Customers();

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setAgent(customer.getAgent());

        for (Orders o : customer.getOrders())
        {
            newCustomer.getOrders().add(new Orders(o.getOrdamount(),
                    o.getAdvanceamount(), newCustomer, o.getOrddescription()));
        }

        return custrepo.save(newCustomer);
    }

    // update by id
    @Override
    public Customers update(Customers customer, long id)
    {
        return custrepo.save(customer);
    }

    // delete by id
   @Override
    public void delete(long id)
    {
        custrepo.deleteById(id);
    }

}
