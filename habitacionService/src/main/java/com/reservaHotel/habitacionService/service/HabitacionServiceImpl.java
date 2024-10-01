package com.reservaHotel.habitacionService.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservaHotel.habitacionService.dto.HabitacionDTO;
import com.reservaHotel.habitacionService.entity.HabitacionEntity;
import com.reservaHotel.habitacionService.repository.HabitacionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HabitacionServiceImpl implements HabitacionService{

	@Autowired
	private HabitacionRepository habitacionRepository;

	@Override
	public List<HabitacionEntity> findAll() {
		List<HabitacionEntity> entities = habitacionRepository.findAll();
		return entities;
	}

	@Override
	public HabitacionEntity findById(Long id) throws Exception{
		Optional<HabitacionEntity> optional = habitacionRepository.findById(id);
		if(optional.isEmpty()) {
			throw new EntityNotFoundException("No existe una habitacion con ese id");
		}else {
			return optional.get();
		}
	}

	@Override
	public HabitacionEntity create(HabitacionEntity entity) {		
		return habitacionRepository.save(entity);
	}

	@Override
	public boolean reservar(Long id) throws Exception{ 
		Optional<HabitacionEntity> optional = habitacionRepository.findById(id);
		if(optional.isEmpty()){
			throw new EntityNotFoundException("No existe una habitacion con ese id");
		}else {
			HabitacionEntity entity = optional.get();
			if(entity.isDisponible()) {
				entity.setDisponible(false);
				habitacionRepository.save(entity);
				return true;
			}else {
				return false;
			}	
		}
	}

	@Override
	public boolean liberar(Long id) {
		Optional<HabitacionEntity> optional = habitacionRepository.findById(id);
		if(optional.isEmpty()){
			throw new EntityNotFoundException("No existe una habitacion con ese id");
		}else {
			HabitacionEntity entity = optional.get();
			if(!entity.isDisponible()) {
				entity.setDisponible(true);
				habitacionRepository.save(entity);
				return true;
			}else {
				return false;
			}
		}	
	}

	@Override
	public void eliminar(Long id) {
		Optional<HabitacionEntity> optional = habitacionRepository.findById(id);
		if(optional.isEmpty()){
			throw new EntityNotFoundException("No existe una habitacion con ese id");
		}else {
			HabitacionEntity entity = optional.get();
			habitacionRepository.delete(entity);
		}
	}
		
	@Override
	public List<HabitacionEntity> filtroPorPrecio(BigDecimal precioMaximo) {
		return habitacionRepository.findByPrecioGreaterThanEqualAndDisponibleTrue(precioMaximo);
	}

	@Override
	public boolean disponible(Long id) {
		Optional<HabitacionEntity> optional = habitacionRepository.findById(id);
		if(optional.isEmpty()) {
			throw new EntityNotFoundException("No existe una habitacion con ese id");
		}else {
			HabitacionEntity entity = optional.get();
			return entity.isDisponible();
		}
	}

	@Override
	public HabitacionEntity modificar(Long id, HabitacionDTO habitacionDTO) throws Exception {
		Optional<HabitacionEntity> optional = habitacionRepository.findById(id);
		if(optional.isEmpty()) {
			throw new EntityNotFoundException("No existe una habitacion con ese id");
		}else {
			HabitacionEntity updateHabitacion = optional.get();
			updateHabitacion.setCapacidad(habitacionDTO.getCapacidad());
			updateHabitacion.setNumero(habitacionDTO.getNumero());
			updateHabitacion.setPiso(habitacionDTO.getPiso());
			updateHabitacion.setPrecio(habitacionDTO.getPrecio());
			
			return habitacionRepository.save(updateHabitacion);
		}
	}
	
	
}
