package com.reservaHotel.habitacionService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservaHotel.habitacionService.entity.HabitacionEntity;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<HabitacionEntity, Long>{
	
	List<HabitacionEntity> findByPrecioGreaterThanEqualAndDisponibleTrue(BigDecimal precioMaximo);
}
