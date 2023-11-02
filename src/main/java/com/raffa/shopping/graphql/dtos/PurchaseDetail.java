package com.raffa.shopping.graphql.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseDetail {
    private Long purchaseId;
    private String customer;
    private String product;
    private int quantity;
}
