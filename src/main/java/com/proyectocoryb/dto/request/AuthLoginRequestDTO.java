package com.proyectocoryb.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthLoginRequestDTO {

    @NotBlank(message = "Usuario es obligatorio")
    private String usuario;
    @NotBlank(message = "Contraseña es obligatoria")
    private String contrasena;
}
