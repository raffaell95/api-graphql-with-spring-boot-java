package com.raffa.shopping.graphql.services;

import com.raffa.shopping.graphql.models.Product;
import com.raffa.shopping.graphql.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;


    public Product findById(Long id){
        return repository.findById(id).orElse(null);
    }


    public List<Product> findAll(){
        return repository.findAll();
    }

    @Transactional
    public Product save(Product p){
        return repository.save(p);
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
