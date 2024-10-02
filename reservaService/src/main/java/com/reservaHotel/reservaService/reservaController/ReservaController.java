package com.reservaHotel.reservaService.reservaController;

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

import com.reservaHotel.reservaService.dto.ReservaDTO;
import com.reservaHotel.reservaService.entity.ReservaEntity;
import com.reservaHotel.reservaService.reservaService.ReservaService;

@RestController
@RequestMapping("/api/reserva")
public class ReservaController {
	
	@Autowired
	private ReservaService reservaService;

	@GetMapping("/getAll")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(reservaService.getAll());
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> crear(@RequestBody ReservaEntity entity){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.crear(entity));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getByUser(@PathVariable Long userId){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(reservaService.getByUserId(userId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		try {
			reservaService.eliminar(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody ReservaDTO dto){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(reservaService.modificar(id, dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
}
