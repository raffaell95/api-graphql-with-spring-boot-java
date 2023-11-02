package com.raffa.shopping.graphql.repositories;

import com.raffa.shopping.graphql.models.Customer;
import com.raffa.shopping.graphql.models.Purchase;
import com.raffa.shopping.graphql.dtos.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query("select c from Purchase c where c.customer = :customer")
    List<Purchase> findAllByClient(@Param("customer") Customer customer);


    @Query("select new com.raffa.shopping.graphql.dtos.PurchaseDetail(c.id,ctm.name,p.name,c.quantity) from Purchase c inner join c.customer ctm inner join c.product p")
    List<PurchaseDetail> findAllPurchaseDetail();
}
