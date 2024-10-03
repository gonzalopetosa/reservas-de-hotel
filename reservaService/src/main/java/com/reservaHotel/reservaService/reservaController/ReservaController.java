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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/api/reserva")
public class ReservaController {
	
	@Autowired
	private ReservaService reservaService;
	
	private final Logger logger = LoggerFactory.getLogger(ReservaController.class);

	@GetMapping("/getAll")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(reservaService.findAll());
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> crear(@RequestBody ReservaEntity entity){
		try {
			ReservaEntity entity2 = reservaService.crear(entity);
			logger.info("Se creo correctamente una reserva {}: ",entity);
			return ResponseEntity.status(HttpStatus.CREATED).body(entity2);
		} catch (Exception e) {
			logger.warn("Hubo un erro al intentar crear una reserva {}: ",e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getByUser(@PathVariable Long userId){
		try {
			List<ReservaEntity> entities = reservaService.findByUserId(userId);
			logger.info("Se encontro correctamente el id del usuario {}: ", userId);
			return ResponseEntity.status(HttpStatus.OK).body(entities);
		} catch (Exception e) {
			logger.warn("El id del ususario no existe {}: ", userId);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		try {
			reservaService.eliminar(id);
			logger.info("Se elimino una reserva {}", reservaService.findById(id));
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			logger.warn("La reserva con id {}: ", id, " no existe");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody ReservaDTO dto){
		try {
			ReservaEntity entity = reservaService.modificar(id, dto);
			logger.info("Se modifico correctamente la reserva {}: ",reservaService.findById(id));
			return ResponseEntity.status(HttpStatus.OK).body(entity);
		} catch (Exception e) {
			logger.warn("Hubo un erro al intentar modificar una reserva {}: ",e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
}
