package com.reservaHotel.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reservaHotel.userService.entity.UserEntity;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

	Optional<UserEntity> findByEmail(String email);

	Optional<UserEntity> findByUsername(String username);
}
