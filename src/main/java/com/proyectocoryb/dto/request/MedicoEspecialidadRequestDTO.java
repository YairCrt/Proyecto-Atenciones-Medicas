package com.proyectocoryb.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MedicoEspecialidadRequestDTO {

    @NotNull(message = "Empleado es obligatorio")
    private Long empleadoId;

    @NotNull(message = "Especialidad es obligatoria")
    private Long especialidadId;
}
