package com.proyectocoryb.dto.response;

import lombok.Data;

@Data
public class MedicoEspecialidadResponseDTO {

    private Long id;
    private EmpleadoResponseDTO empleado;
    private EspecialidadResponseDTO especialidad;
}
