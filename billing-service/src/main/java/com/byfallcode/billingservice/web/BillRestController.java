package com.byfallcode.billingservice.web;

import com.byfallcode.billingservice.entities.Bill;
import com.byfallcode.billingservice.feign.CustomerRestClient;
import com.byfallcode.billingservice.feign.ProductRestClient;
import com.byfallcode.billingservice.repositories.BillRepository;
import com.byfallcode.billingservice.repositories.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private ProductRestClient productRestClient;
    @Autowired
    private CustomerRestClient customerRestClient;

    @GetMapping("/bills/{id}")
    public Bill getBillById(@PathVariable Long id) {
        Bill bill = toBillResponse(billRepository.findById(id).get());

        return bill;
    }

    private Bill toBillResponse(Bill bill) {
        bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(productRestClient.findProductById(productItem.getProductId()));
        });
        return bill;
    }

}
