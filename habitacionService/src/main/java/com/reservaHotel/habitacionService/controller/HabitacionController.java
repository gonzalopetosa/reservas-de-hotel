package com.reservaHotel.habitacionService.controller;

import java.math.BigDecimal;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reservaHotel.habitacionService.dto.HabitacionDTO;
import com.reservaHotel.habitacionService.entity.HabitacionEntity;
import com.reservaHotel.habitacionService.service.HabitacionService;


@RestController
@RequestMapping("/api/habitacion")
public class HabitacionController {

	@Autowired
	private HabitacionService habitacionService;
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(habitacionService.findAll());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(habitacionService.findById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/disponible/{id}")
	public ResponseEntity<?> disponible(@PathVariable Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(habitacionService.disponible(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/reservar/{id}")
	public ResponseEntity<?> reservar(@PathVariable Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(habitacionService.reservar(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/liberar/{id}")
	public ResponseEntity<?> liberar(@PathVariable Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(habitacionService.liberar(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/disponibles")
	public ResponseEntity<?> disponiblesPorPrecio(@RequestParam BigDecimal precioMaximo){
		return ResponseEntity.status(HttpStatus.OK).body(habitacionService.filtroPorPrecio(precioMaximo));
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody HabitacionEntity entity){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(habitacionService.create(entity));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		try {
			habitacionService.eliminar(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}	
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody HabitacionDTO dto){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(habitacionService.modificar(id, dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
		
}
