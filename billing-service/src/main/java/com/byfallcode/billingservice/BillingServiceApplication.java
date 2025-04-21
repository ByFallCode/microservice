package com.byfallcode.billingservice;

import com.byfallcode.billingservice.entities.Bill;
import com.byfallcode.billingservice.entities.ProductItem;
import com.byfallcode.billingservice.feign.CustomerRestClient;
import com.byfallcode.billingservice.feign.ProductRestClient;
import com.byfallcode.billingservice.model.Customer;
import com.byfallcode.billingservice.model.Product;
import com.byfallcode.billingservice.repositories.BillRepository;
import com.byfallcode.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			BillRepository billRepository,
			ProductItemRepository productItemRepository,
			ProductRestClient productRestClient,
			CustomerRestClient customerRestClient
	) {
		return args -> {
			Collection<Customer> customers = customerRestClient.getAllCustomers().getContent();
			Collection<Product> products = productRestClient.getAllProducts().getContent();

			customers.forEach(c -> {
				Bill bill = Bill.builder()
						.billingDate(new Date())
						.customerId(c.getId())
						.build();
				billRepository.save(bill);

				products.forEach(p -> {
					ProductItem productItem = ProductItem.builder()
							.productId(p.getId())
							.price(p.getPrice())
							.quantity(1+new Random().nextInt(10))
							.bill(bill)
							.build();
					productItemRepository.save(productItem);
				});
			});
		};
	}
}
