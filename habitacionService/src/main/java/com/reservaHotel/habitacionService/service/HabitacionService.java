package com.reservaHotel.habitacionService.service;

import com.reservaHotel.habitacionService.dto.HabitacionDTO;
import com.reservaHotel.habitacionService.entity.HabitacionEntity;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface HabitacionService {
	
	List<HabitacionEntity> findAll();
	HabitacionEntity findById(Long id) throws Exception;
	HabitacionEntity create(HabitacionEntity entity);
	boolean reservar(Long id) throws Exception;
	boolean liberar(Long id) throws Exception;
	boolean disponible(Long id) throws Exception;
	void eliminar(Long id) throws Exception;
	List<HabitacionEntity> filtroPorPrecio(BigDecimal precioMaximo);
	HabitacionEntity modificar(Long id, HabitacionDTO habitacionDTO) throws Exception;
}
