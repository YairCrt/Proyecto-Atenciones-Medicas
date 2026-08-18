package com.proyectocoryb.dto.response;

import com.proyectocoryb.model.Estado;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AtencionResponseDTO {

    private Long id;
    private LocalDateTime fecha;
    private String motivo;
    private PacienteResponseDTO paciente;
    private EmpleadoResponseDTO empleado;
    private Estado estado;
}
