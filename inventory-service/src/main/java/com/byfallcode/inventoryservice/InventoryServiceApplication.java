package com.byfallcode.inventoryservice;

import com.byfallcode.inventoryservice.entities.Product;
import com.byfallcode.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository) {
		return args -> {
			productRepository.save(Product.builder().id(UUID.randomUUID().toString()).name("PC").price(1000).quantity(10).build());
			productRepository.save(Product.builder().id(UUID.randomUUID().toString()).name("Clavier").price(500).quantity(10).build());
			productRepository.save(Product.builder().id(UUID.randomUUID().toString()).name("Souris").price(1300).quantity(10).build());
			productRepository.findAll().forEach(customer -> System.out.println(customer.toString()));
		};
	}
}
