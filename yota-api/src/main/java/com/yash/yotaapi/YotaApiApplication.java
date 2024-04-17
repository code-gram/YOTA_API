package com.yash.yotaapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "YASH ONLINE TEST APPLICATION"))
@EnableScheduling
public class YotaApiApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(YotaApiApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(YotaApiApplication.class);
    }
}
