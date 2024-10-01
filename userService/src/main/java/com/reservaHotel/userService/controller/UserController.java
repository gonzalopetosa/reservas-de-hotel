package com.reservaHotel.userService.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservaHotel.userService.entity.UserEntity;
import com.reservaHotel.userService.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		Optional<UserEntity> user = userService.findById(id);
		if(user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(user);
		}
	}

	@GetMapping("/get/email/{email}")
	public ResponseEntity<?> getByEmail(@PathVariable String email){
		UserEntity user = userService.findByEmail(email).get();
		if(user.equals(null)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(user);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody UserEntity userEntity) throws Exception{
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.crearUsuario(userEntity));
	}
	
	@DeleteMapping("/eliminar/{email}")
	public ResponseEntity<?> eliminar(@PathVariable String email){
		Optional<UserEntity> entity = userService.findByEmail(email);
		if(entity.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			userService.eliminar(entity.get());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
}
