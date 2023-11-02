package com.raffa.shopping.graphql.repositories;

import com.raffa.shopping.graphql.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
