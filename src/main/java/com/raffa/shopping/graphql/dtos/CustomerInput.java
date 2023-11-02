package com.raffa.shopping.graphql.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInput {

    private Long id;
    private String name;
    private String email;
}


