package com.beet.receipt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ReceiptApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceiptApiApplication.class, args);
	}

}
