package org.inneo.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

public class SecurityConfig {
	@SuppressWarnings("removal")
	@Bean
	  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
	    serverHttpSecurity
	        .csrf().disable()
	        .authorizeExchange(exchange ->	        
	        	exchange.pathMatchers("/eureka/**")
		            .permitAll()
		            .anyExchange()
		            .authenticated())
	        .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
	        return serverHttpSecurity.build();
	    }
}
