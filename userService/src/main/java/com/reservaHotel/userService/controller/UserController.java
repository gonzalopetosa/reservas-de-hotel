package com.reservaHotel.userService.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservaHotel.userService.dto.UserDTO;
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
		try {
			UserEntity user = userService.findById(id);
			return ResponseEntity.status(HttpStatus.OK).body(user);
		} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
		}
	}

	@GetMapping("/get/email/{email}")
	public ResponseEntity<?> getByEmail(@PathVariable String email){
		try {
			UserEntity user = userService.findByEmail(email);
			return ResponseEntity.status(HttpStatus.OK).body(user);
		} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody UserEntity userEntity) throws Exception{
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.crearUsuario(userEntity));			
		} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody UserDTO userDTO){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.modificar(id, userDTO));
		} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
		
	@DeleteMapping("/eliminar/{email}")
	public ResponseEntity<?> eliminar(@PathVariable String email){
		try {
			userService.eliminar(email);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
		}
	}
	
	
}
