package com.reservaHotel.habitacionService.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservaHotel.habitacionService.entity.HabitacionEntity;
import com.reservaHotel.habitacionService.repository.HabitacionRepository;

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
	public Optional<HabitacionEntity> findById(Long id) {
		return habitacionRepository.findById(id);
	}

	@Override
	public HabitacionEntity create(HabitacionEntity entity) {
		return habitacionRepository.save(entity);
	}


	@Override
	public boolean reservar(HabitacionEntity entity) {
		if(entity.isDisponible()) {
			entity.setDisponible(false);
			habitacionRepository.save(entity);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean liberar(HabitacionEntity entity) {
		if(!entity.isDisponible()) {
			entity.setDisponible(true);
			habitacionRepository.save(entity);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void eliminar(HabitacionEntity entity) {
		habitacionRepository.delete(entity);
	}

	@Override
	public List<HabitacionEntity> filtroPorPrecio(BigDecimal precioMaximo) {
		return habitacionRepository.findByPrecioGreaterThanEqualAndDisponibleTrue(precioMaximo);
	}

	@Override
	public boolean disponible(Long id) {
		HabitacionEntity entity = habitacionRepository.findById(id).get();
		return entity.isDisponible();
	}
	
}
