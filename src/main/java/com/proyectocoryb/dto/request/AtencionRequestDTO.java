package com.proyectocoryb.dto.request;

import com.proyectocoryb.model.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AtencionRequestDTO {

    @NotNull(message = "Fecga de atención requerida")
    private LocalDateTime fecha;

    @NotBlank(message = "Motivo de la atencion requerido")
    private String motivo;

    @NotNull(message = "El paciente es obligatorio")
    private Long pacienteId;

    @NotNull(message = "El empleado es obligatorio")
    private Long empleadoId;

    @NotNull(message = "El estado es requerido")
    private Estado estado;
}
