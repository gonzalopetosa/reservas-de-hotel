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
import java.util.Optional;

import com.reservaHotel.reservaService.clients.HabitacionClient;
import com.reservaHotel.reservaService.clients.UserClient;
import com.reservaHotel.reservaService.dto.HabitacionDTO;
import com.reservaHotel.reservaService.dto.UserDTO;
import com.reservaHotel.reservaService.entity.ReservaEntity;
import com.reservaHotel.reservaService.reservaService.ReservaService;

import feign.FeignException;

@RestController
@RequestMapping("/api/reserva")
public class ReservaController {
	
	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private HabitacionClient habitacionClient;
	
	@Autowired
	private UserClient userClient;

	@GetMapping("/getAll")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(reservaService.getAll());
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> crear(@RequestBody ReservaEntity entity){
		try { 
			userClient.getUserId(entity.getUsuarioId());
			if(habitacionClient.disponible(entity.getHabitacionId()) == false){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La habitacion no esta disponible");
			}
			habitacionClient.reservar(entity.getHabitacionId());
			return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.crear(entity));
		
		} catch (FeignException.NotFound e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario o habitacion no existen");	
		}
		catch (FeignException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body("Error al comunicarse con el microservicio: " + e.getMessage());
		}
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getByUser(@PathVariable Long userId){
		return ResponseEntity.status(HttpStatus.OK).body(reservaService.getByUserId(userId));
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		Optional<ReservaEntity> optional = reservaService.getById(id);
		if(optional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			habitacionClient.liberar(optional.get().getHabitacionId());
			reservaService.eliminar(optional.get());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody ReservaEntity entity){
		Optional<ReservaEntity> optional = reservaService.getById(id);
		if(optional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			ReservaEntity updateReserva = optional.get();
			updateReserva.setFechaInicio(entity.getFechaInicio());
			updateReserva.setFechaFin(entity.getFechaFin());
			
			if(habitacionClient.disponible(entity.getHabitacionId()) == false){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La habitacion no esta disponible");
			}
			updateReserva.setHabitacionId(entity.getHabitacionId());
			updateReserva.setEstado(entity.getEstado());
		}
		return ResponseEntity.status(HttpStatus.OK).body(reservaService.crear(entity));
	}
	
}
