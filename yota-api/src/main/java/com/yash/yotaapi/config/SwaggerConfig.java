package com.yash.yotaapi.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This SwaggerConfic component will be used for API Documentation. Do not change anything over here. 
 * It will be a global configuration. 
 * visit the swagger ui on : swagger-ui.html link.
 * @author pankaj.ssharma
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	
	/**
	 * This method basically scans all api's under specified packages for documentation
	 */
	

	@Bean
	Docket apiDocumentation()
	{
		return new Docket(DocumentationType.SWAGGER_2).
				apiInfo(getInfo()).
					select().
					apis(RequestHandlerSelectors.basePackage("com.yash")).
					build();
	}

	
	  private ApiInfo getInfo() {
	  
	  return new ApiInfo("YOTA App",
	  "This project is developed by YASH Technologies", "1.0", "Term of Service",
	  new Contact("YASH Technologies", "www.hash.com", "support@yash.com"), "",
	  "",Collections.emptyList()); }
	 
}
