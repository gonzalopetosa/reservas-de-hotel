package com.reservaHotel.userService.service;

import com.reservaHotel.userService.dto.UserDTO;
import com.reservaHotel.userService.entity.UserEntity;

import java.util.List;


public interface UserService {
	
	UserEntity crearUsuario(UserEntity usuario) throws Exception;
	UserEntity findByEmail(String email) throws Exception;
	List<UserEntity> findAll();
	UserEntity findById(Long id) throws Exception;
	void eliminar(String email) throws Exception;
	UserEntity modificar(Long id, UserDTO userDTO) throws Exception;
}
