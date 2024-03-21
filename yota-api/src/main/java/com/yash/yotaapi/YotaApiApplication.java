package com.yash.yotaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title="YASH ONLINE TEST APPLICATION"))
public class YotaApiApplication 	
	
	  extends SpringBootServletInitializer {    
		  @Override   
		  // Configuring method has to be overridden    
		  protected SpringApplicationBuilder    
		    configure(SpringApplicationBuilder application){        
		    return application.sources(            
		    		YotaApiApplication.class);    
		  }  
	public static void main(String[] args) {
		SpringApplication.run(YotaApiApplication.class, args);
	}
}
