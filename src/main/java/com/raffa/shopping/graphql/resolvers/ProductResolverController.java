package com.raffa.shopping.graphql.resolvers;

import com.raffa.shopping.graphql.models.Product;
import com.raffa.shopping.graphql.dtos.ProductInput;
import com.raffa.shopping.graphql.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductResolverController {

    @Autowired
    private ProductService service;

    @QueryMapping
    public Product product(@Argument Long id){
        return service.findById(id);
    }

    @QueryMapping
    public List<Product> products(){
        return service.findAll();
    }

    @MutationMapping
    public Product saveProduct(@Argument(name = "product") ProductInput input){
        var mapper = new ModelMapper();
        var product = mapper.map(input, Product.class);
        return service.save(product);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id){
        return service.deleteById(id);
    }

    @SchemaMapping
    public String realAmount(Product product){
        return "R$ " + product.getAmount();
    }

}
