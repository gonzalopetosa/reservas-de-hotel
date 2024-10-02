package com.reservaHotel.reservaService.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReservaDTO {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado; 
}
