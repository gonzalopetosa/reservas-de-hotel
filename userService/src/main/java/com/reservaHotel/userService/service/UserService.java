package com.reservaHotel.userService.service;

import com.reservaHotel.userService.entity.UserEntity;

import java.util.List;
import java.util.Optional;


public interface UserService {
	
	UserEntity crearUsuario(UserEntity usuario) throws Exception;
	Optional<UserEntity> findByEmail(String email);
	List<UserEntity> findAll();
	Optional<UserEntity> findById(Long id);
	void eliminar(UserEntity entity);
}
