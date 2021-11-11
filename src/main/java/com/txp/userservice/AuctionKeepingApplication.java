package com.txp.userservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
@EnableOpenApi
public class AuctionKeepingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuctionKeepingApplication.class, args);
	}

}
