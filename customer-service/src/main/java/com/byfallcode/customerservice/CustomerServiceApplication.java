package com.byfallcode.customerservice;

import com.byfallcode.customerservice.entities.Customer;
import com.byfallcode.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner init(CustomerRepository repository) {
		return args -> {
			repository.save(Customer.builder().name("Fall").email("fall@mail.com").build());
			repository.save(Customer.builder().name("Fall").email("fall@mail.com").build());
			repository.save(Customer.builder().name("Fall").email("fall@mail.com").build());
			repository.findAll().forEach(customer -> System.out.println(customer.toString()));
		};
	}

}
