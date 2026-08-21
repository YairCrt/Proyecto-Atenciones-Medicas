package com.proyectocoryb.mapper;

import com.proyectocoryb.dto.request.EmpleadoRequestDTO;
import com.proyectocoryb.dto.response.EmpleadoResponseDTO;
import com.proyectocoryb.mapper.config.MapperConfiguration;
import com.proyectocoryb.model.Empleado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class, uses = {PersonaMapper.class})
public interface EmpleadoMapper {

    @Mapping(source = "personaId", target = "persona.id")
    Empleado toEntity(EmpleadoRequestDTO dto);

    EmpleadoResponseDTO toResponse(Empleado entity);

    @Mapping(source = "personaId", target = "persona.id")
    void updateEntity(@MappingTarget Empleado entity, EmpleadoRequestDTO dto);
}
