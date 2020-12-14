package com.onlineshop.onlineshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class OnlineshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineshopApplication.class, args);
	}

}
