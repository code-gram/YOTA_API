package com.yash.yotaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class YotaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(YotaApiApplication.class, args);
	}

}
