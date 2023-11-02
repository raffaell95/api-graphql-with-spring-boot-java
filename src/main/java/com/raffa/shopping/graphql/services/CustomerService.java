package com.raffa.shopping.graphql.services;

import com.raffa.shopping.graphql.models.Customer;
import com.raffa.shopping.graphql.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;


    public Customer findById(Long id){
        return repository.findById(id).orElse(null);
    }


    public List<Customer> findAll(){
        return repository.findAll();
    }

    @Transactional
    public Customer save(Customer c){
        return repository.save(c);
    }

    @Transactional
    public Boolean deleteById(Long id){
        if (repository.findById(id).isPresent()){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

}
