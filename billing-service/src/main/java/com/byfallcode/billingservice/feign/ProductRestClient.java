package com.byfallcode.billingservice.feign;

import com.byfallcode.billingservice.model.Customer;
import com.byfallcode.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface ProductRestClient {
    @GetMapping("/api/products/{id}")
    Product findProductById(@PathVariable String id);


    @GetMapping("/api/products")
    PagedModel<Product> getAllProducts();
}
