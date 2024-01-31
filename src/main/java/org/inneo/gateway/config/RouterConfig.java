package org.inneo.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("cep", r -> r
					.path("/cep/**")
					.filters(f -> f.rewritePath("/cep/(?<cep>.*)", "/api/cep/v1/${cep}"))
					.uri("https://brasilapi.com.br"))
				.build();				
	}
}
