package com.proyectocoryb.dto.response;

import com.proyectocoryb.model.Estado;
import lombok.Data;

@Data
public class PersonaResponseDTO {

    private Long id;
    private String nombre;
    private String email;
    private Estado estado;
}
