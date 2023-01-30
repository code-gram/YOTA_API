package com.yash.yotaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class YotaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(YotaApiApplication.class, args);
	}

}
