package com.stackroute.ApiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableEurekaClient
@CrossOrigin
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}


	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/user-service/**")
						.filters(f->f.dedupeResponseHeader("Access-Control-Allow-Origin","RETAIN_UNIQUE"))
						.uri("lb://USER-SERVICE")
				)
				.route(r -> r.path("/bank-service/**")
						.filters(f->f.dedupeResponseHeader("Access-Control-Allow-Origin","RETAIN_UNIQUE"))
						.uri("lb://BANK-SERVICE")
				)
				.route(r -> r.path("/transaction-service/**")
						.filters(f->f.dedupeResponseHeader("Access-Control-Allow-Origin","RETAIN_UNIQUE"))
						.uri("lb://TRANSACTION-SERVICE")
				)
				.route(r -> r.path("/**")
						.filters(f->f.dedupeResponseHeader("Access-Control-Allow-Origin","RETAIN_UNIQUE"))
						.uri("lb://PRODUCT-WEBAPP-SERVICE")
				)
				.build();

	}

}
