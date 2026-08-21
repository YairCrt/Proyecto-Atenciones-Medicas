package com.proyectocoryb.mapper;

import com.proyectocoryb.dto.request.EspecialidadRequestDTO;
import com.proyectocoryb.dto.response.EspecialidadResponseDTO;
import com.proyectocoryb.mapper.config.MapperConfiguration;
import com.proyectocoryb.model.Especialidad;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class)
public interface EspecialidadMapper {

    Especialidad toEntity(EspecialidadRequestDTO dto);

    EspecialidadResponseDTO toResponse(Especialidad entity);

    void updateEntity(@MappingTarget Especialidad entity, EspecialidadRequestDTO dto);

}
