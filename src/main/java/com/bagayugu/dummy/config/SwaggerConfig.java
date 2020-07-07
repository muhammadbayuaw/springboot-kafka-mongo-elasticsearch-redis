package com.bagayugu.dummy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	public static final String ELASTIC_CONTROLLER = "elastic-controller";
	public static final String PRODUCT_CONTROLLER = "product-controller";
	
	@Bean
	public Docket dummyApi() {
		return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bagayugu.dummy.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .tags(new Tag(ELASTIC_CONTROLLER, "Elasticsearch service endpoint"))
				.tags(new Tag(PRODUCT_CONTROLLER, "Mongo & Redis service endpoint"));
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfo(
                "Dummy API",
                "Dummy API",
                "1.0",
                "Terms of service",
                new Contact("Muhammad Bayu Agusto Widodo", "https://linkedin.com/muhammadbayuaw", "muhammadbayu201@gmail.com"),
               "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
	}
}
