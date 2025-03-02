package org.sujal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
		return http
					.authorizeHttpRequests(authz ->
					{
						authz
							.requestMatchers("/auth").permitAll()
							.anyRequest().authenticated();
					}
							).httpBasic(withDefaults())
							 .csrf().disable()
							 .build();
	}
}