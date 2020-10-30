package com.rowdyruff.smarthack.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build().globalOperationParameters(operationParameters());
	}
	
	private List<Parameter> operationParameters() {
		List<Parameter> headers = new ArrayList<>();
		headers.add(new ParameterBuilder().name("Authorization")
				.description("Enter the valid JWT")
				.modelRef(new ModelRef("string")).parameterType("header")
				.required(false).build());
		
		return headers;
	}
}