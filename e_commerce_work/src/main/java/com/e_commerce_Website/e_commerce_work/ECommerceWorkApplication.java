package com.e_commerce_Website.e_commerce_work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableCaching
@EnableJpaAuditing
public class ECommerceWorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceWorkApplication.class, args);
	}

}
