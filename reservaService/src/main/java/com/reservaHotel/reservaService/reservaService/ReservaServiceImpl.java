package com.reservaHotel.reservaService.reservaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservaHotel.reservaService.entity.ReservaEntity;
import com.reservaHotel.reservaService.reservaRepository.ReservaRepository;

@Service
public class ReservaServiceImpl implements ReservaService{

	@Autowired
	private ReservaRepository reservaRepository;
		
	
	@Override
	public ReservaEntity crear(ReservaEntity entity) {
		return reservaRepository.save(entity);
	}

	@Override
	public List<ReservaEntity> getAll() {
		List<ReservaEntity> entities = reservaRepository.findAll();
		return entities;
	}

	@Override
	public Optional<ReservaEntity> getById(Long id) {
		Optional<ReservaEntity> optional = reservaRepository.findById(id);
		return optional;
	}

	@Override
	public List<ReservaEntity> getByUserId(Long userId) {
		// TODO Auto-generated method stub
		List<ReservaEntity> entities = reservaRepository.findAll();
		List<ReservaEntity> reservasUsuario = new ArrayList<ReservaEntity>();
		
		for(ReservaEntity r : entities) {
			if(r.getUsuarioId().equals(userId)) {
				reservasUsuario.add(r);
			}
		}
		return reservasUsuario;
	}

	@Override
	public void eliminar(ReservaEntity entity) {
		reservaRepository.delete(entity);
	}

}
