package com.reservaHotel.userService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservaHotel.userService.entity.UserEntity;
import com.reservaHotel.userService.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserEntity crearUsuario(UserEntity usuario) throws Exception {
		if(usuario.getEdad() >= 18) {
			return userRepository.save(usuario);	
		}else {
			throw new Exception("Para crear un usuario se debe ser mayor de 18 años");
		}
	
	}

	@Override
	public Optional<UserEntity> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<UserEntity> findAll() {
		List<UserEntity> entities = userRepository.findAll();
		return entities;
	}

	@Override
	public void eliminar(UserEntity entity) {
		userRepository.delete(entity);
	}

	@Override
	public Optional<UserEntity> findById(Long id) {
		return userRepository.findById(id);
	}


	
}
