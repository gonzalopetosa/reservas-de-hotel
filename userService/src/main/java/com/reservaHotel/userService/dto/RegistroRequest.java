package com.reservaHotel.userService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroRequest {

    private String email;
    private String password;
    private String username;
    private Integer dni;
    private int edad;
    private String telefono;
    private String direccion;
}
