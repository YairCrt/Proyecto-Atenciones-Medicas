package com.proyectocoryb.mapper;

import com.proyectocoryb.dto.request.UsuarioRequestDto;
import com.proyectocoryb.dto.response.UsuarioResponseDTO;
import com.proyectocoryb.mapper.config.MapperConfiguration;
import com.proyectocoryb.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class, uses = {PersonaMapper.class})
public interface UsuarioMapper {

    @Mapping(source = "personaId", target = "persona.id")
    Usuario toEntity(UsuarioRequestDto dto);

    UsuarioResponseDTO toResponse(Usuario entity);

    @Mapping(source = "personaId", target = "persona.id")
    void updateEntity(@MappingTarget Usuario entity, UsuarioRequestDto dto);
}
