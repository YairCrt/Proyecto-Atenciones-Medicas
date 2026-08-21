package com.proyectocoryb.mapper;

import com.proyectocoryb.dto.request.PacienteRequestDTO;
import com.proyectocoryb.dto.response.PacienteResponseDTO;
import com.proyectocoryb.mapper.config.MapperConfiguration;
import com.proyectocoryb.model.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class, uses = {PersonaMapper.class})
public interface PacienteMapper {

    @Mapping(source = "personaId", target = "persona.id")
    Paciente toEntity(PacienteRequestDTO dto);

    PacienteResponseDTO toResponse(Paciente entity);

    @Mapping(source = "personaId", target = "persona.id")
    void updateEntity(@MappingTarget Paciente entity, PacienteRequestDTO dto);

}
