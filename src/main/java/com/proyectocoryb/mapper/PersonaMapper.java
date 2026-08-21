package com.proyectocoryb.mapper;


import com.proyectocoryb.dto.request.PersonaRequestDTO;
import com.proyectocoryb.dto.response.PersonaResponseDTO;
import com.proyectocoryb.mapper.config.MapperConfiguration;
import com.proyectocoryb.model.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class)
public interface PersonaMapper {

    Persona toEntity(PersonaRequestDTO dto);

    PersonaResponseDTO toResponse(Persona entity);

    void updateEntity(@MappingTarget Persona entity, PersonaRequestDTO dto);
}
