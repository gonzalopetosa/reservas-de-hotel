package com.reservaHotel.userService.controller;

import com.reservaHotel.userService.dto.LoginRequest;
import com.reservaHotel.userService.dto.RegistroRequest;
import com.reservaHotel.userService.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	
    @PostMapping ("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest Request) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(Request));
    }

    @PostMapping ("/register")
    public ResponseEntity<?> registrar(@RequestBody RegistroRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.register(request));
    }

}
