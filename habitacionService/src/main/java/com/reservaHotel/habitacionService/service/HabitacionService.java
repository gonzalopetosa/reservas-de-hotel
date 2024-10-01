package com.reservaHotel.habitacionService.service;

import com.reservaHotel.habitacionService.entity.HabitacionEntity;
import java.util.Optional;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface HabitacionService {
	
	List<HabitacionEntity> findAll();
	Optional<HabitacionEntity> findById(Long id);
	HabitacionEntity create(HabitacionEntity entity);
	boolean reservar(HabitacionEntity entity);
	boolean liberar(HabitacionEntity entity);
	boolean disponible(Long id);
	void eliminar(HabitacionEntity entity);
	List<HabitacionEntity> filtroPorPrecio(BigDecimal precioMaximo);
}
