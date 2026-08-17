package com.proyectocoryb.dto.request;

import com.proyectocoryb.model.Rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthRegisterRequestDTO {

    @NotBlank(message = "Usuario es obligatorio")
    private String usuario;

    @NotBlank(message = "Contraseña es obligatoria")
    private String contrasena;

    @NotNull(message = "Rol es obligatorio")
    private Rol rol;

    @NotNull(message = "Persona es obligatoria")
    private Long personaId;
}
