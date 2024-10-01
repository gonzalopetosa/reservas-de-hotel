package com.reservaHotel.userService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservaHotel.userService.dto.UserDTO;
import com.reservaHotel.userService.entity.UserEntity;
import com.reservaHotel.userService.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

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

	@Override
	public UserEntity modificar(Long id, UserDTO userDTO) throws Exception{
		Optional<UserEntity> optional = userRepository.findById(id);
		if(optional.isEmpty()) {
			throw new EntityNotFoundException("Usuario no encontrado");
		}else {
			UserEntity updateUser = optional.get();
			updateUser.setDireccion(userDTO.getDireccion());
			updateUser.setEmail(userDTO.getEmail());
			updateUser.setTelefono(userDTO.getTelefono());
			
			return userRepository.save(updateUser);
		}
	}


	
}
