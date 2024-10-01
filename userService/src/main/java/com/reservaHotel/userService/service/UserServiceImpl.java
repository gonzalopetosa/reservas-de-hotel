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
		Optional<UserEntity> optionalemail = userRepository.findByEmail(usuario.getEmail());
		if(optionalemail.isEmpty() && usuario.getEdad() >= 18) {
			return userRepository.save(usuario);	
		}else if(usuario.getEdad() < 18){
			throw new Exception("Para crear un usuario se debe ser mayor de 18 años");
		}else {
			throw new Exception("El email ya esta registrado para un usuario");
		}
	}

	@Override
	public UserEntity findByEmail(String email) throws Exception{
		Optional<UserEntity> optional = userRepository.findByEmail(email);
		if(optional.isEmpty()) {
			throw new EntityNotFoundException("Usuario no encontrado");			
		}else {
			return optional.get();
		}	
	}

	@Override
	public List<UserEntity> findAll() {
		List<UserEntity> entities = userRepository.findAll();
		return entities;
	}

	@Override
	public void eliminar(String email) throws Exception{
		Optional<UserEntity> optional = userRepository.findByEmail(email);
		if(optional.isEmpty()) {
			throw new EntityNotFoundException("Usuario no encontrado"); 
		}else {
			userRepository.delete(optional.get());	
		}
		
	}

	@Override
	public UserEntity findById(Long id) {
		Optional<UserEntity> optional = userRepository.findById(id);
		if(optional.isEmpty()) {
			throw new EntityNotFoundException("Usuario no encontrado");			
		}else {
			return optional.get();
		}	}

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
			//compruebo que el email nuevo no este usado
			Optional<UserEntity> entity = userRepository.findByEmail(userDTO.getEmail());
			if(!entity.isEmpty()) {
				throw new Exception("El email ya esta registrado para un usuario");
			}
			return userRepository.save(updateUser);				
		}
	}


	
}
