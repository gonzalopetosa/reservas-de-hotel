package com.reservaHotel.reservaService.reservaService;

import java.util.List;
import com.reservaHotel.reservaService.dto.ReservaDTO;
import com.reservaHotel.reservaService.entity.ReservaEntity;

public interface ReservaService {

	ReservaEntity crear(ReservaEntity entity) throws Exception;
	List<ReservaEntity> findAll();
	ReservaEntity findById(Long Id) throws Exception;
	List<ReservaEntity> findByUserId(Long userId) throws Exception;
	void eliminar(Long id) throws Exception;
	ReservaEntity modificar(Long id, ReservaDTO dto) throws Exception;
}
