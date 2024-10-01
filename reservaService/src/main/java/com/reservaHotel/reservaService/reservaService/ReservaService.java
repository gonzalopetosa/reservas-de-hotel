package com.reservaHotel.reservaService.reservaService;

import java.util.List;
import java.util.Optional;

import com.reservaHotel.reservaService.entity.ReservaEntity;

public interface ReservaService {

	ReservaEntity crear(ReservaEntity entity);
	List<ReservaEntity> getAll();
	Optional<ReservaEntity> getById(Long Id);
	List<ReservaEntity> getByUserId(Long userId);
	void eliminar(ReservaEntity entity);
}
