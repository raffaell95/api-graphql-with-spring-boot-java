package com.raffa.shopping.graphql.resolvers;

import com.raffa.shopping.graphql.dtos.CustomerInput;
import com.raffa.shopping.graphql.models.Customer;
import com.raffa.shopping.graphql.models.Purchase;
import com.raffa.shopping.graphql.services.CustomerService;
import com.raffa.shopping.graphql.services.PurchaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CustomerResolverController {

    @Autowired
    private CustomerService service;

    @Autowired
    private PurchaseService purchaseService;

    @QueryMapping
    public Customer customer(@Argument Long id){
        return service.findById(id);
    }

    @QueryMapping
    public List<Customer> customers(){
        return service.findAll();
    }

    @MutationMapping
    public Customer saveCustomer(@Argument(name = "customer") CustomerInput input){
        var mapper = new ModelMapper();
        var customer = mapper.map(input, Customer.class);
        return service.save(customer);
    }

    @MutationMapping
    public Boolean deleteCustomer(@Argument Long id){
        return service.deleteById(id);
    }

    @SchemaMapping(typeName = "Customer", field = "purchases")
    public List<Purchase> purchases(Customer customer){
        return purchaseService.findAllByClient(customer);
    }

}
