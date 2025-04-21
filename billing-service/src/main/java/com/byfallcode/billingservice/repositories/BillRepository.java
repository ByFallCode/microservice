package com.byfallcode.billingservice.repositories;

import com.byfallcode.billingservice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface BillRepository extends JpaRepository<Bill, Long> {
}
