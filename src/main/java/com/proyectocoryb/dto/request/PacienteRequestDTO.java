package com.proyectocoryb.dto.request;

import com.proyectocoryb.model.Estado;
import com.proyectocoryb.model.Rol;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PacienteRequestDTO {

    @NotNull(message = "Persona es obligatoria")
    private Long personaId;

    @NotNull(message = "Rol es obligatorio")
    private Rol rol;

    @NotNull(message = "Estado es obligatorio")
    private Estado estado;
}
