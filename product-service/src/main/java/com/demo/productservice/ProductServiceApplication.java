package com.demo.productservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication {

	@Value("${spring.data.mongodb.uri}")
    private static String mongoDbUri;

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
		System.out.println("MongoDB URI: " + mongoDbUri);
	}

}
