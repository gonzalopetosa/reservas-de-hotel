package com.reservaHotel.reservaService.clients;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.reservaHotel.reservaService.dto.HabitacionDTO;

@FeignClient(name = "microservicio-habitacion")
public interface HabitacionClient {

	@GetMapping("api/habitacion/disponible/{id}")
	boolean disponible(@PathVariable Long id);
	
	@PutMapping("api/habitacion/reservar/{id}")
	boolean reservar(@PathVariable Long id);
	
	@PutMapping("api/habitacion/liberar/{id}")
	boolean liberar(@PathVariable Long id);
}
