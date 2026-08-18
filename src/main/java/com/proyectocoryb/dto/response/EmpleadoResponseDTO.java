package com.proyectocoryb.dto.response;

import com.proyectocoryb.model.Estado;
import com.proyectocoryb.model.Rol;
import lombok.Data;

@Data
public class EmpleadoResponseDTO {

    private Long id;
    private PersonaResponseDTO persona;
    private Rol rol;
    private Estado estado;
}
