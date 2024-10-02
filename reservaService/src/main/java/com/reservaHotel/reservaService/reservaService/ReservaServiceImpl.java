package com.reservaHotel.reservaService.reservaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservaHotel.reservaService.clients.HabitacionClient;
import com.reservaHotel.reservaService.clients.UserClient;
import com.reservaHotel.reservaService.dto.ReservaDTO;
import com.reservaHotel.reservaService.entity.ReservaEntity;
import com.reservaHotel.reservaService.reservaRepository.ReservaRepository;

import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ReservaServiceImpl implements ReservaService{

	@Autowired
	private ReservaRepository reservaRepository;
		
	@Autowired
	private HabitacionClient habitacionClient;
	
	@Autowired
	private UserClient userClient;
	
	@Override
	public ReservaEntity crear(ReservaEntity entity) throws Exception {
		try {	
			userClient.getUserId(entity.getUsuarioId());
			
			if(habitacionClient.disponible(entity.getHabitacionId()) == false) {
			throw new Exception("La habitacion se encuentra reservada");
			}
			habitacionClient.reservar(entity.getHabitacionId());
			return reservaRepository.save(entity);
		} catch (FeignException.NotFound e) {
			throw new EntityNotFoundException("El usuario o habitacion no existen");	 
		}	
	}

	@Override
	public List<ReservaEntity> getAll() {
		List<ReservaEntity> entities = reservaRepository.findAll();
		return entities;
	}

	@Override
	public ReservaEntity getById(Long id) throws Exception{
		Optional<ReservaEntity> optional = reservaRepository.findById(id);
		if(optional.isEmpty()) {
			throw new EntityNotFoundException("No existe reserva con ese id");
		}
		return optional.get();
	}

	@Override
	public List<ReservaEntity> getByUserId(Long userId) throws Exception{

		try{
			
			userClient.getUserId(userId);
			List<ReservaEntity> entities = reservaRepository.findAll();
			List<ReservaEntity> reservasUsuario = new ArrayList<ReservaEntity>();

			for(ReservaEntity r : entities) {
				if(r.getUsuarioId().equals(userId)) {
					reservasUsuario.add(r);
				}
			}
			return reservasUsuario;
		}catch(FeignException.NotFound e) {
			throw new EntityNotFoundException("El usuario no existe");
		}
	}

	@Override
	public void eliminar(Long id) throws Exception{
		Optional<ReservaEntity> optional = reservaRepository.findById(id);
		if(optional.isEmpty()) {
			throw new EntityNotFoundException("No existe reserva con ese id");
		}
		habitacionClient.liberar(optional.get().getHabitacionId());
		reservaRepository.delete(optional.get());
		
	}

	
	@Override
	public ReservaEntity modificar(Long id, ReservaDTO dto) throws Exception{
		Optional<ReservaEntity> optional = reservaRepository.findById(id);
		if(optional.isEmpty()) {
			throw new EntityNotFoundException("No existe reserva con ese id");
		}else {
			ReservaEntity updateReserva = optional.get();
			updateReserva.setEstado(dto.getEstado());
			updateReserva.setFechaFin(dto.getFechaFin());
			updateReserva.setFechaInicio(dto.getFechaInicio());
			
			return reservaRepository.save(updateReserva);
		}
	}
	
}
