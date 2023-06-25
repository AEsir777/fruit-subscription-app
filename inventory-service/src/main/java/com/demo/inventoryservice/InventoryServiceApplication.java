package com.demo.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.inventoryservice.model.Inventory;
import com.demo.inventoryservice.repository.InventoryRepository;

@SpringBootApplication
public class InventoryServiceApplication {
	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			if (inventoryRepository.count() == 0) {
				Inventory inventory = new Inventory();
				inventory.setSkuCode("orange");
				inventory.setQuantity(100);

				Inventory inventory2 = new Inventory();
				inventory2.setSkuCode("blueberry");
				inventory2.setQuantity(190);

				inventoryRepository.save(inventory);
				inventoryRepository.save(inventory2);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
		
	}

}
