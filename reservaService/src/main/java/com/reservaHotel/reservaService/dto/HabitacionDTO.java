package com.reservaHotel.reservaService.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HabitacionDTO {

	private Long id;
	private boolean disponible;
	private BigDecimal precio;
	
}
