package com.byfallcode.billingservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    String id;
    String name;
    private  double price;
    private int quantity;
}
