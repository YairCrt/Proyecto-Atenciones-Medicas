package com.proyectocoryb.mapper;

import com.proyectocoryb.dto.request.MedicoEspecialidadRequestDTO;
import com.proyectocoryb.dto.response.MedicoEspecialidadResponseDTO;
import com.proyectocoryb.mapper.config.MapperConfiguration;
import com.proyectocoryb.model.MedicoEspecialidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class, uses = {EmpleadoMapper.class, EspecialidadMapper.class})
public interface MedicoEspecialidadMapper {

    @Mapping(source = "empleadoId", target = "empleado.id")
    @Mapping(source = "especialidadId", target = "especialidad.id")
    MedicoEspecialidad toEntity(MedicoEspecialidadRequestDTO dto);

    MedicoEspecialidadResponseDTO toResponse(MedicoEspecialidad entity);

    @Mapping(source = "empleadoId", target = "empleado.id")
    @Mapping(source = "especialidadId", target = "especialidad.id")
    void updateEntity(@MappingTarget MedicoEspecialidad entity, MedicoEspecialidadRequestDTO dto);
}
