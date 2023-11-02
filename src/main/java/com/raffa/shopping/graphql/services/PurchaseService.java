package com.raffa.shopping.graphql.services;

import com.raffa.shopping.graphql.models.Customer;
import com.raffa.shopping.graphql.models.Purchase;
import com.raffa.shopping.graphql.dtos.PurchaseDetail;
import com.raffa.shopping.graphql.exceptions.DomainException;
import com.raffa.shopping.graphql.repositories.PurchaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository repository;


    public Purchase findById(Long id){
        return repository.findById(id).orElse(null);
    }


    public List<Purchase> findAll(PageRequest pageRequest){
        return repository.findAll(pageRequest).getContent();
    }

    @Transactional
    public Purchase save(Purchase c){
        if(c.getQuantity() > 100){
            throw new DomainException("It is not possible to create an account with more than 100 items");
        }
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

    public List<Purchase> findAllByClient(Customer cliente) {
        return repository.findAllByClient(cliente);
    }

    public List<PurchaseDetail> findAllPurchaseDetail() {
        return repository.findAllPurchaseDetail();
    }
}
