package com.byfallcode.billingservice.model;

import lombok.*;

@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class Customer {
    private Long id;
    private String name;
    private String email;
}
