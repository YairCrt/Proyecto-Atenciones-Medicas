package com.proyectocoryb.dto.request;

import com.proyectocoryb.model.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EspecialidadRequestDTO {

    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;

    @NotNull(message = "Estado es obligatorio")
    private Estado estado;
}
