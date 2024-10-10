package com.reservaHotel.habitacionService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	return http.
    			csrf(csrf -> csrf.disable())
    			.authorizeHttpRequests(authRequests -> 
                authRequests
                    // Permitir el acceso sin autenticación a la ruta específica
                    .requestMatchers(HttpMethod.GET, "/api/habitacion/getAll").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/habitacion/**").permitAll() // Permitir acceso a otras rutas GET de usuario
                    .anyRequest().authenticated() // Proteger el resto de las rutas
            )
    			.build();
    }
	
}
