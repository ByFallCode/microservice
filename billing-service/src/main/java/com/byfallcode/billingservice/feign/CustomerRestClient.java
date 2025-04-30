package com.byfallcode.billingservice.feign;

import com.byfallcode.billingservice.model.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "customer-service")
public interface CustomerRestClient {
    @GetMapping("/api/customers/{id}")
    @CircuitBreaker(name = "CustomerServicCB", fallbackMethod = "getDefaultCustomer")
    Customer findCustomerById(@PathVariable Long id);

    @GetMapping("/api/customers")
    @CircuitBreaker(name = "CustomerServicCBB", fallbackMethod = "getDefaultCustomers")
    PagedModel<Customer> getAllCustomers();

    default Customer getDefaultCustomer(Long id, Exception e) {
        return new Customer(id, "Default name", "Default mail");
    }

    default PagedModel<Customer> getDefaultCustomers(Exception e) {
        return PagedModel.empty();
    }
}
