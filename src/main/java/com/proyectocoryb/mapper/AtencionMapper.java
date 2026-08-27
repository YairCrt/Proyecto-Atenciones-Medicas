package com.proyectocoryb.mapper;

import com.proyectocoryb.dto.request.AtencionRequestDTO;
import com.proyectocoryb.dto.response.AtencionResponseDTO;
import com.proyectocoryb.mapper.config.MapperConfiguration;
import com.proyectocoryb.model.Atencion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class, uses = {PacienteMapper.class, EmpleadoMapper.class})
public interface AtencionMapper {

    @Mapping(source = "pacienteId", target = "paciente.id")
    @Mapping(source = "empleadoId", target = "empleado.id")
    Atencion toEntity(AtencionRequestDTO dto);

    AtencionResponseDTO toResponse(Atencion entity);

    @Mapping(source = "pacienteId", target = "paciente.id")
    @Mapping(source = "empleadoId", target = "empleado.id")
    void updateEntity(@MappingTarget Atencion entity, AtencionRequestDTO dto);
}
