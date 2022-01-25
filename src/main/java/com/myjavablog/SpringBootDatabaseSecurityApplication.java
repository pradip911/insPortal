package com.myjavablog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
//@EnableSwagger2
public class SpringBootDatabaseSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDatabaseSecurityApplication.class, args);
	}
	/*
	 * @Bean public Docket productApi() { return new
	 * Docket(DocumentationType.SPRING_WEB.SWAGGER_2).select()
	 * .apis(RequestHandlerSelectors.basePackage("com.myjavablog.controller")).build
	 * (); }
	 */
}

