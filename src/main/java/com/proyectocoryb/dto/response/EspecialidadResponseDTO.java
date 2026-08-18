package com.proyectocoryb.dto.response;

import com.proyectocoryb.model.Estado;
import lombok.Data;

@Data
public class EspecialidadResponseDTO {

    private Long id;
    private String nombre;
    private Estado estado;
}
