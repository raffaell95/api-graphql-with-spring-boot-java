package com.raffa.shopping.graphql.resolvers;

import com.raffa.shopping.graphql.models.Purchase;
import com.raffa.shopping.graphql.dtos.PurchaseInput;
import com.raffa.shopping.graphql.dtos.PurchaseDetail;
import com.raffa.shopping.graphql.services.CustomerService;
import com.raffa.shopping.graphql.services.PurchaseService;
import com.raffa.shopping.graphql.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

@Controller
public class PurchaseResolverController {

    @Autowired
    private PurchaseService service;
    @Autowired
    private CustomerService clientService;

    @Autowired
    private ProductService productService;

    @QueryMapping
    public List<Purchase> purchases(@Argument int page, @Argument int size){
        return service.findAll(PageRequest.of(page, size,
                Sort.by("quantity").descending()));
    }

    @QueryMapping
    public Purchase purchase(@Argument Long id){
        return service.findById(id);
    }

    @MutationMapping
    public Purchase savePurchase(@Argument("purchase") PurchaseInput input){
        var mapper = new ModelMapper();
        var purchase = mapper.map(input, Purchase.class);

        purchase.setDate(new Date());

        purchase.setCustomer(clientService.findById(input.getCustomerId()));
        purchase.setProduct(productService.findById(input.getProductId()));

        return service.save(purchase);

    }

    @MutationMapping
    public Boolean deletePurchase(@Argument Long id){
        return service.deleteById(id);
    }

    @SchemaMapping(typeName = "Purchase", field = "status")
    public String status(Purchase purchase){
        return "Current Status : " + purchase.getStatus();
    }

    @QueryMapping(name = "purchaseDetail")
    public List<PurchaseDetail> getPurchaseDetail(){
        return service.findAllPurchaseDetail();
    }

}
