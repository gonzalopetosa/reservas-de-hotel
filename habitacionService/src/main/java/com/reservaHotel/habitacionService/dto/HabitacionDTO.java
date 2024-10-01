package com.reservaHotel.habitacionService.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HabitacionDTO {

	private int numero;
	private int piso;
	private int capacidad;
	private BigDecimal precio;	
}
