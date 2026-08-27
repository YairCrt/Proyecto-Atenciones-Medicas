package com.proyectocoryb.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioRequestDto {

    @NotBlank(message = "Usuario es obligatorio")
    private String usuario;

    @NotBlank(message = "Contraseña es obligatoria")
    private String contrasena;

    @NotNull(message = "La persona es requerida")
    private Long personaId;
}
