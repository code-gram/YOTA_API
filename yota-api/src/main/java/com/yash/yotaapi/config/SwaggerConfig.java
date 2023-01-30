package com.yash.yotaapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
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
					select().
					apis(RequestHandlerSelectors.basePackage("com.yash")).
					build();
	}
}
