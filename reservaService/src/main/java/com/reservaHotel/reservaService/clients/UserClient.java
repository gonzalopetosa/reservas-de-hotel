package com.reservaHotel.reservaService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.reservaHotel.reservaService.dto.UserDTO;


@FeignClient(name = "microservicio-usuario")
public interface UserClient {

	@GetMapping("api/user/get/{id}")	
	UserDTO getUserId(@PathVariable Long id);
	
}
