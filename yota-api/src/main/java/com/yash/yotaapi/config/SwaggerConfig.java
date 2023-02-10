package com.yash.yotaapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This SwaggerConfic component will be used for API Documentation. Do not change anything over here. 
 * It will be a global configuration. 
 * visit the swagger ui on : swagger-ui.html link.
 * @author nitin.chougale
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	
	/**
	 * This method basically scans all api's under specified packages for documentation
	 * @return Docket Provides sensible defaults and convenience methods for configuration.
	 */
	@Bean
	Docket apiDocumentation()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.yash"))
				.build();
	}
	/**
	 * This method gives title to our swagger documentation page with version
	 * @return ApiInfo 
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Associate Management Swagger Configuration")
				.description("Swagger Configuration for application")
				.version("1.0.0")
				.build();
	}
}
