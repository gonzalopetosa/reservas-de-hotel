package com.reservaHotel.userService.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.reservaHotel.userService.dto.AuthResponse;
import com.reservaHotel.userService.dto.LoginRequest;
import com.reservaHotel.userService.dto.RegistroRequest;
import com.reservaHotel.userService.entity.Role;
import com.reservaHotel.userService.entity.UserEntity;
import com.reservaHotel.userService.jwt.JwtService;
import com.reservaHotel.userService.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	public AuthResponse register(RegistroRequest request) {
		UserEntity user = UserEntity.builder()
	            .dni(request.getDni())
	            .direccion(request.getDireccion())
	            .edad(request.getEdad())
	            .email(request.getEmail())
	            .password(passwordEncoder.encode(request.getPassword()) )
	            .telefono(request.getTelefono())
	            .username(request.getUsername())
	            .role(Role.USER)
	            .build();

	        userRepository.save(user);
	        return AuthResponse.builder()
	        		.token(jwtService.getToken(user))
	        		.build();
	}
	
	public AuthResponse login(LoginRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
		String token = jwtService.getToken(user);
		return AuthResponse.builder()
				.token(token)
				.build();
	}
	

}
